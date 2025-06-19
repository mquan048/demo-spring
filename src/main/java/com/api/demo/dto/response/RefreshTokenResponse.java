package com.api.demo.dto.response;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class RefreshTokenResponse {
    private String token;
    private String refreshToken;
}
