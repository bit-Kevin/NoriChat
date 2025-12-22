package io.github.kevvy.chat_java.aspect;

import io.github.kevvy.chat_java.annotation.GlobalInterceptor;
import io.github.kevvy.chat_java.common.exception.BusinessException;
import io.github.kevvy.chat_java.entity.constants.Constants;
import io.github.kevvy.chat_java.entity.dto.TokenUserInfoDto;
import io.github.kevvy.chat_java.entity.enums.ResponseCodeEnums;
import io.github.kevvy.chat_java.redis.RedisUtil;
import io.github.kevvy.chat_java.utils.StringUtil;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.lang.reflect.Method;
import java.util.prefs.BackingStoreException;

@Aspect
@Component
public class GlobalOperationAspect {
    @Resource
    private RedisUtil redisUtil ;
    private static final Logger logger = LoggerFactory.getLogger(GlobalOperationAspect.class);

    /**
     * 定义切面，并选择在切点方法之前执行切面方法
     */
    @Before("@annotation(io.github.kevvy.chat_java.annotation.GlobalInterceptor)")//切点
    public void interceptorDo(JoinPoint joinPoint){
        try {
            Method method = ((MethodSignature)joinPoint.getSignature()).getMethod();
            GlobalInterceptor interceptor = method.getAnnotation(GlobalInterceptor.class);
            if (interceptor==null){
                return;
            }
            //根据拦截器配置进行校验
            if (interceptor.checkAdmin()||interceptor.checkLogin()){
                checkLogin(interceptor.checkAdmin());
            }
        } catch (BusinessException e) {
            logger.error("全局验证拦截器：{}", e.getMessage());
        }
    }

    private void checkLogin(boolean checkAdmin){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes != null) {
            HttpServletRequest request = attributes.getRequest();
            String token = request.getHeader("token");
            if (StringUtil.isEmpty(token)){
                //用户没有登录
                throw  new BusinessException(ResponseCodeEnums.code_900);
            }
            TokenUserInfoDto userInfoDto = (TokenUserInfoDto) redisUtil.get(Constants.REDIS_KEY_WS_TOKEN + token);
            //redis 里面有没记录，登录超时
            if (userInfoDto==null){
                throw  new BusinessException(ResponseCodeEnums.code_900);
            }
            //redis 里面登录记录，再校验是否为管理员
            if (checkAdmin&&!userInfoDto.isAdmin()){
                throw new BusinessException(ResponseCodeEnums.code_404);
            }

        }
    }
}
