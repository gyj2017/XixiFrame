package com.gyj.api.util;

import com.gyj.api.constant.JwtConstant;
import io.jsonwebtoken.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtUtil {

    /**
     * 生成 JWT Token
     *
     * @param subject 用户ID
     * @return JWT Token
     */
    public static String generateToken(String subject) {
        Map<String, Object> claims = new HashMap<>();
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + JwtConstant.JWT_EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, JwtConstant.JWT_SECRET_KEY)
                .compact();
    }

    /**
     * 解析 JWT Token
     *
     * @param token JWT Token
     * @return 用户ID
     */
    public static String parseToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(JwtConstant.JWT_SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }

    /**
     * 验证 JWT Token 是否有效
     *
     * @param token JWT Token
     * @return true: 有效；false：无效
     */
    public static boolean validateToken(String token) {
        try {
            // 解析 JWT Token
            Jws<Claims> claims = Jwts.parser().setSigningKey(JwtConstant.JWT_SECRET_KEY).parseClaimsJws(token);
            String userId = claims.getBody().getSubject();
            Date expiration = claims.getBody().getExpiration();

            // 检查过期时间
            if (expiration.before(new Date())) {
                return false;
            }

            // TODO: 进行其他自定义验证

            // 验证通过返回 true
            return true;

        } catch (JwtException | IllegalArgumentException e) {
            // 如果发生异常则认为无效
            return false;
        }
    }
}
