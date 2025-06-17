package org.ysu.mall.util;



import io.jsonwebtoken.*;
import org.ysu.mall.enums.ResultEnum;
import org.ysu.mall.exception.BusinessException;

import java.util.Date;

public class JwtUtil {
    private static final String SECRET_KEY = "secret";
    private static final long EXPIRATION_TIME = 86400000; // 24 hours

    public static String generateToken(String Id,String userId) {
        return Jwts.builder()
                .setId(Id)
                .setSubject(userId)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();
    }

    public static Claims parseToken(String token) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(SECRET_KEY)
                    .parseClaimsJws(token)
                    .getBody();
            return claims;
        } catch (ExpiredJwtException e) {
            // 明确处理过期异常
            throw new BusinessException(ResultEnum.UNAUTHORIZED, "Token expired");
        } catch (UnsupportedJwtException | MalformedJwtException | SignatureException e) {
            // 处理无效令牌
            throw new BusinessException(ResultEnum.UNAUTHORIZED, "Invalid token");
        } catch (IllegalArgumentException e) {
            // 令牌为空等非法参数
            throw new BusinessException(ResultEnum.UNAUTHORIZED, "Token cannot be empty");
        } catch (Exception e) {
            // 其他未知异常（可选：记录日志）
            throw new BusinessException(ResultEnum.INTERNAL_ERROR, "Unexpected error");
        }
    }
}
