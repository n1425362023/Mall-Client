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
    // ====== 控制开关：是否禁用 JWT 认证 ======
    private static final boolean DISABLE_JWT = true;

    // 安全密钥（未禁用时仍然使用）
    private static final SecretKey SECRET_KEY = Keys.hmacShaKeyFor(
            "secret".repeat(8).getBytes(StandardCharsets.UTF_8)
    );
    private static final long EXPIRATION_TIME = 86400000; // 24 hours

    // 生成 token
    public static String generateToken(Integer userID, String userName) {
        if (DISABLE_JWT) {
            // 模拟返回一个假 token
            return "mock-token";
        }

        return Jwts.builder()
                .setId(userID.toString())
                .setSubject(userName)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SECRET_KEY, SignatureAlgorithm.HS384)
                .compact();
    }

    // 解析 token
    public static Claims parseToken(String token) {
        if (DISABLE_JWT) {
            // 模拟一个固定用户的 claims
            Claims claims = Jwts.claims();
            claims.setId("1");
            claims.setSubject("MockUser");
            claims.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME));
            return claims;
        }

        try {
            return Jwts.parserBuilder()
                    .setSigningKey(SECRET_KEY)
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