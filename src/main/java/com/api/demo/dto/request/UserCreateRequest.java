package com.api.demo.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserCreateRequest {
    @NotEmpty(message = "Username must not be empty")
    @Size(min = 3, message = "Username must be at least 3 characters")
    private String username;

    @NotEmpty(message = "Password must not be empty")
    @Size(min = 8, message = "Password must be at least 8 characters")
    private String password;

    @NotEmpty(message = "Firstname must not be empty")
    @Size(min = 3, message = "First name must be at least 3 characters")
    private String firstname;

    @NotEmpty(message = "Lastname must not be empty")
    @Size(min = 3, message = "Last name must be at least 3 characters")
    private String lastname;
}
