package io.github.kevvy.chat_java.filter;

import io.github.kevvy.chat_java.context.UserContext;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class UserContextClearFilter extends OncePerRequestFilter {
    //OncePerRequestFilter 对“每一个 HTTP 请求”，这个 Filter 只会执行一次 ,保证一次请求只清一次 ThreadLocal
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        try {
            // 让请求继续执行（Controller / Service / AOP / 抛异常都在里面）
            filterChain.doFilter(request, response);
        } finally {
            // 不管发生什么，最后一定执行
            UserContext.remove();
        }
        // “先放行，让它继续跑；
        // 等整个请求生命周期结束了，
        // finally 再统一清理 ThreadLocal。”
    }

}
