package com.security.auth.security.jwt;

import com.security.auth.model.User;
import com.security.auth.security.util.JWTUtil;
import io.jsonwebtoken.Claims;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public final class JWTUserFactory {

    private JWTUserFactory() {
    }

    public static JWTUser create(User user){
        return JWTUser.builder()
                .username(user.getEmail())
                .password(user.getPassword())
                .roles(user.getRoles())
                .authorities(mapToGrantedAuthorities(user.getRoles()))
                .build();
    }

    public static JWTUser getJwtUserFromToken(List<String> roles, String token) {
        Claims claims = JWTUtil.getAllClaims(token);
        return JWTUser.builder()
                .token(token)
                .username(claims.getSubject())
                .roles(roles)
                .authorities(mapToGrantedAuthorities(roles))
                .build();
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(List<String> roles) {
        return roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }
}
