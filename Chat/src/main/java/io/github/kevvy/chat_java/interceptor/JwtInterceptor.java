package io.github.kevvy.chat_java.interceptor;


import io.github.kevvy.chat_java.utils.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * JWT 拦截器
 * 用于拦截所有需要登录的接口请求，校验 Authorization 中的 Token 是否有效
 */
@Component
@RequiredArgsConstructor //  自动生成构造方法并注入依赖
public class JwtInterceptor implements HandlerInterceptor {

    private final JwtUtil jwtUtil;

    /**
     * 在 Controller 方法执行前执行此方法，用于在请求进入 Controller 前验证 Token
     * @param request  HTTP 请求对象（可取出 Header、参数等）
     * @param response HTTP 响应对象
     * @param handler  当前要执行的处理器（Controller 方法）
     * @return 返回 true 代表放行；false 代表拦截（不再进入 Controller）
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        // 1️⃣ 从请求头中获取 token
        String token = request.getHeader("Authorization");

        // 2️⃣ 如果 token 为空，则直接拒绝请求（返回 401）
        if (token == null || token.isEmpty()) {
            response.setStatus(401);
            return false;
        }

        try {
            // 3️⃣ 解析 token，验证是否有效（会抛出异常表示无效或过期）
            String username = jwtUtil.parseToken(token);

            // 4️⃣ 如果解析成功，将用户名放入请求属性中，方便后续 Controller 使用
            request.setAttribute("username", username);

            // 5️⃣ 放行请求
            return true;

        } catch (Exception e) {
            // 6️⃣ token 无效或过期，返回 401 状态码
            response.setStatus(401);
            return false;
        }
    }
}

