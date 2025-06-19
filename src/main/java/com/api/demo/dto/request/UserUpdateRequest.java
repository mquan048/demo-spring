package com.api.demo.dto.request;

import jakarta.validation.constraints.Size;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserUpdateRequest {
    @Size(min = 3, message = "First name must be at least 3 characters")
    private String firstname;

    @Size(min = 3, message = "Last name must be at least 3 characters")
    private String lastname;
}
