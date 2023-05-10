package com.gyj.api.util;

import com.gyj.api.constant.JwtConstant;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

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
}
