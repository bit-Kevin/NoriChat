package io.github.kevvy.chat_java.utils;

import java.util.Date;

import io.github.kevvy.chat_java.entity.config.JwtProperties;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
@Component
@RequiredArgsConstructor //  自动生成构造方法并注入依赖
public class JwtUtil {

//    private static final String SECRET = "KevvySecretKey-K3vvyS3cur3Key-ForJWT!!"; // ≥32字节

    private final JwtProperties jwtProperties;

    // 生成Token
    /**
     * 生成 Token
     */
    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + jwtProperties.getExpireTime()))
                .signWith(Keys.hmacShaKeyFor(jwtProperties.getSecret().getBytes(StandardCharsets.UTF_8)),
                        SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * 解析 Token
     */
    public String parseToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(jwtProperties.getSecret().getBytes(StandardCharsets.UTF_8)))
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}



