package cn.navg.laboratory.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JWTutils {
    // 密钥信息
    private static final String SECRET_KEY = "aHR0eWpz987";
    // 7天过期时间
    private static final long EXPIRATION_TIME = 7 * 24 * 60 * 60 * 1000;

    /**
     * 生成JWT令牌
     *
     * @param claims 令牌中包含的信息
     * @return 生成的JWT令牌字符串
     */
    public static String createToken(Map<String, Object> claims) {
        return Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .addClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .compact();
    }

    /**
     * 解析JWT令牌
     *
     * @param token 要解析的JWT令牌字符串
     * @return 包含令牌信息的Claims对象
     * @throws Exception 如果令牌无效或已过期，则抛出异常
     */
    public static Claims ParseToken(String token) throws Exception {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
    }
}
