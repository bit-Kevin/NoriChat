package io.github.kevvy.chat_java.config;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Jwt配置读取类
 * 自动读取 application.yml 中的 jwt 节点配置
 */
@Data
@Component
@ConfigurationProperties(prefix = "jwt")  // 指定配置前缀
public class JwtProperties {

    /** JWT密钥（从yml读取） */
    private String secret;

    /** Token有效期（毫秒） */
    private long expireTime;
}

