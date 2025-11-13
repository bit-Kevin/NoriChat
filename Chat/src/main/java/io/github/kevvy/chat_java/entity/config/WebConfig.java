package io.github.kevvy.chat_java.entity.config;

import io.github.kevvy.chat_java.interceptor.JwtInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Web 配置类
 * 主要用于注册自定义拦截器（例如 JWT 验证拦截器）
 */
@Configuration
@RequiredArgsConstructor // 自动生成构造方法并注入依赖
public class WebConfig implements WebMvcConfigurer {

    // 注入我们自定义的 JwtInterceptor（Spring 会自动管理该 Bean）
    private final JwtInterceptor jwtInterceptor;

    /**
     * 添加拦截器配置
     * @param registry 拦截器注册器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtInterceptor) // 注册拦截器
                .addPathPatterns("/api/**")     // 拦截所有 /api 开头的请求
                // 以下接口不需要拦截（放行登录、注册）
                .excludePathPatterns("/api/auth/**");
    }
}

