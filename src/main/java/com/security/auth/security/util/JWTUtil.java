package com.security.auth.security.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Map;
import javax.crypto.SecretKey;

public class JWTUtil {
    private static final String SECRET_KEY = "your-secure-secret-key-min-32bytes";
    private static final SecretKey KEY = Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));

    public static String generateToken(String username, long expiryMinutes) {
        return Jwts.builder()
                .subject(username)
                .claims(Map.of("user", username, "role","ROLE_USER"))
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + expiryMinutes * 60_000L))
                .signWith(KEY, Jwts.SIG.HS256)
                .compact();
    }

    public static String validateAndExtractUsername(String token) {
        try {
            Claims claims = Jwts.parser()
                    .verifyWith(KEY)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
            return claims.getSubject();
        } catch (JwtException | IllegalArgumentException e) {
            return null;
        }
    }

    public static boolean isValidToken(String token){
        try {
            Jwts.parser().verifyWith(KEY).build().parseSignedClaims(token);
            return true;
        }catch (JwtException | IllegalArgumentException e) {
            return false;
        }

    }

    public static Claims getAllClaims(String token){
        return Jwts.parser()
                .verifyWith(KEY)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }


}
