package com.api.demo.service;

import java.util.Date;
import java.util.Objects;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import com.api.demo.dto.request.LoginRequest;
import com.api.demo.exception.AppException;
import com.api.demo.model.InvalidatedToken;
import com.api.demo.model.User;
import com.api.demo.repository.InvalidatedTokenRepository;
import com.api.demo.repository.UserRepository;
import com.api.demo.security.JwtTokenProvide;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;

    private final InvalidatedTokenRepository invalidatedTokenRepository;

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);

    private final JwtTokenProvide jwtTokenProvide;

    public String authenticate(LoginRequest request) {
        User user = userRepository
                .findByUsername(request.getUsername())
                .orElseThrow(() -> new AppException("Username or password is incorrect"));
        boolean result = passwordEncoder.matches(request.getPassword(), user.getPassword());

        if (!result) throw new AppException("Username or password is incorrect");

        return jwtTokenProvide.generateToken(user);
    }

    public void invalidateToken(Jwt jwt) {
        InvalidatedToken invalidatedToken = InvalidatedToken.builder()
                .id(jwt.getId())
                .expireTime(Date.from(Objects.requireNonNull(jwt.getExpiresAt())))
                .build();

        invalidatedTokenRepository.save(invalidatedToken);
    }

    //    public RefreshTokenResponse refreshToken(Jwt refreshJwt) {
    //        invalidateToken(refreshJwt);
    //
    //        String username = refreshJwt.getSubject();
    //
    //        User user = userRepository.findByUsername(username).orElseThrow(NotFoundException::new);
    //
    //        String token = jwtTokenProvide.generateToken(user);
    //
    //        return null;
    //    }
}
