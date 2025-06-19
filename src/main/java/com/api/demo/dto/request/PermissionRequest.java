package com.api.demo.dto.request;

import jakarta.validation.constraints.NotEmpty;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class PermissionRequest {
    @NotEmpty(message = "Name is not empty")
    private String name;

    private String description;
}
