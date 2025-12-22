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
@Aspect
@Component
public class GlobalOperationAspect {
    @Resource
    private RedisUtil redisUtil;
    private static final Logger logger = LoggerFactory.getLogger(GlobalOperationAspect.class);

    /**
     * 定义切点切面，并选择在切点方法之前执行切面方法
     */
    @Before("@annotation(io.github.kevvy.chat_java.annotation.GlobalInterceptor)")//切点
    public void interceptorDo(JoinPoint joinPoint) {
        //代理触发 AOP Spring 构造一个 JoinPoint 现场快照，把：目标对象，方法签名，参数塞进去
        try {
            //AOP 拿到 即将执行的方法 （设计方法注解时基本会用到这个代码）
            //Signature 是一个接口，表示：“方法/构造器的签名信息” Signature 是父接口
            //对“方法”而言，真实类型是 MethodSignature，有getMethod()方法才能真正拿到jvm中的方法
            Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();

            //通过反射，读取 方法上的权限配置（权限配置是指：设计注解时所写的值）
            GlobalInterceptor globalInterceptor = method.getAnnotation(GlobalInterceptor.class);
            if (globalInterceptor == null) {
                return;
            }
            //根据拦截器配置进行校验
            if (globalInterceptor.checkAdmin() || globalInterceptor.checkLogin()) {
                checkLogin(globalInterceptor.checkAdmin());
            }
        } catch (BusinessException e) {
            logger.error("全局验证拦截器：{}", e.getMessage());
        }
    }

    private void checkLogin(boolean checkAdmin) {
        //通过 ThreadLocal，拿到当前请求。 Spring 在每个 HTTP 请求进来时，把 request 放进一个 ThreadLocal
        //Holder = “上下文存储容器”， ThreadLocal = 实现这种容器的“技术手段”
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes != null) {
            HttpServletRequest request = attributes.getRequest();
            //从 Header 拿 token
            String token = request.getHeader("token");
            if (StringUtil.isEmpty(token)) {
                //用户没有登录
                throw new BusinessException(ResponseCodeEnums.code_900);
            }
            TokenUserInfoDto userInfoDto = (TokenUserInfoDto) redisUtil.get(Constants.REDIS_KEY_WS_TOKEN + token);
            //redis 里面有没记录，登录超时
            if (userInfoDto == null) {
                throw new BusinessException(ResponseCodeEnums.code_900);
            }
            //redis 里面登录记录，再校验是否为管理员
            if (checkAdmin && !userInfoDto.isAdmin()) {
                throw new BusinessException(ResponseCodeEnums.code_404);
            }

        }
    }
}
