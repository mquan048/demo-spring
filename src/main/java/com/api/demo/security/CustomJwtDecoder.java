package com.api.demo.security;

import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.stereotype.Component;

import com.api.demo.repository.InvalidatedTokenRepository;

@Component
public class CustomJwtDecoder implements JwtDecoder {

    @Value("${env.security.jwt-secret}")
    private String JWT_SECRET;

    private NimbusJwtDecoder nimbusJwtDecoder = null;

    @Autowired
    private InvalidatedTokenRepository invalidatedTokenRepository;

    @Override
    public Jwt decode(String token) throws JwtException {
        if (nimbusJwtDecoder == null) {
            SecretKeySpec secretKeySpec = new SecretKeySpec(JWT_SECRET.getBytes(), "HS512");

            nimbusJwtDecoder = NimbusJwtDecoder.withSecretKey(secretKeySpec)
                    .macAlgorithm(MacAlgorithm.HS512)
                    .build();
        }

        Jwt jwt = nimbusJwtDecoder.decode(token);

        if (invalidatedTokenRepository.existsById(jwt.getId())) throw new BadJwtException("Invalid token");
        return jwt;
    }
}
