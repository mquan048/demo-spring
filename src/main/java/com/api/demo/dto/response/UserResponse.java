package com.api.demo.dto.response;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserResponse {
    private String id;
    private String username;
    private String password;
    private String firstname;
    private String lastname;
}
