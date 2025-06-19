package com.api.demo.security;

import java.util.Date;
import java.util.HashMap;
import java.util.StringJoiner;
import java.util.UUID;
import javax.crypto.SecretKey;

import jakarta.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.api.demo.model.User;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtTokenProvide {

    @Value("${env.security.jwt-secret}")
    private String JWT_SECRET;

    @Value("${env.security.jwt-expiration}")
    private long JWT_EXPIRATION;

    @Value("${env.security.refresh-expiration}")
    private long REFRESH_EXPIRATION;

    private SecretKey SECRET_KEY;

    @PostConstruct
    public void initSecretKey() {
        this.SECRET_KEY = Keys.hmacShaKeyFor(JWT_SECRET.getBytes());
    }

    public String generateToken(User user) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + JWT_EXPIRATION);

        HashMap<String, Object> claims = new HashMap<>();
        claims.put("scope", buildRoles(user));

        return Jwts.builder()
                .setSubject(user.getUsername())
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .addClaims(claims)
                .setId(UUID.randomUUID().toString())
                .signWith(SECRET_KEY, SignatureAlgorithm.HS512)
                .compact();
    }

    public String buildRoles(User user) {
        StringJoiner stringJoiner = new StringJoiner(" ");
        if (!CollectionUtils.isEmpty(user.getRoles()))
            user.getRoles().forEach(role -> {
                stringJoiner.add("ROLE_" + role.getName());
                if (!CollectionUtils.isEmpty(role.getPermissions()))
                    role.getPermissions().forEach(permission -> stringJoiner.add(permission.getName()));
            });
        return stringJoiner.toString();
    }
}
