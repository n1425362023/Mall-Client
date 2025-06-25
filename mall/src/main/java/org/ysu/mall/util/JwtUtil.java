package org.ysu.mall.util;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.ysu.mall.enums.ResultEnum;
import org.ysu.mall.exception.BusinessException;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

public class JwtUtil {
    // 使用安全的密钥生成方式
    private static final SecretKey SECRET_KEY = Keys.hmacShaKeyFor(
            "secret".repeat(8)
                    .getBytes(StandardCharsets.UTF_8)
    );
    private static final long EXPIRATION_TIME = 86400000; // 24 hours

    public static String generateToken(Integer userID, String userName) {
        return Jwts.builder()
                .setId(userID.toString())
                .setSubject(userName)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SECRET_KEY, SignatureAlgorithm.HS384)
                .compact();
    }

    public static Claims parseToken(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(SECRET_KEY) // 使用Key对象
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (ExpiredJwtException e) {
            throw new BusinessException(ResultEnum.UNAUTHORIZED, "Token expired");
        } catch (UnsupportedJwtException | MalformedJwtException | SignatureException e) {
            throw new BusinessException(ResultEnum.UNAUTHORIZED, "Invalid token");
        } catch (IllegalArgumentException e) {
            throw new BusinessException(ResultEnum.UNAUTHORIZED, "Token cannot be empty");
        } catch (Exception e) {
            throw new BusinessException(ResultEnum.INTERNAL_ERROR, "Unexpected error");
        }
    }
}