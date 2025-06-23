package com.api.demo.controller;

import java.util.List;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.api.demo.dto.request.PermissionRequest;
import com.api.demo.dto.response.ApiResponse;
import com.api.demo.model.Permission;
import com.api.demo.service.PermissionService;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/permission")
@RequiredArgsConstructor
@Tag(name = "Permission", description = "Permission management APIs")
@SecurityRequirement(name = "bearerAuth")
public class PermissionController {
    private final PermissionService permissionService;

    @PostMapping
    public ResponseEntity<ApiResponse<Permission>> createPermission(@RequestBody @Valid PermissionRequest request) {
        Permission permission = permissionService.create(request);
        ApiResponse<Permission> response = ApiResponse.<Permission>builder()
                .status(HttpStatus.CREATED.value())
                .data(permission)
                .build();
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Permission>>> getPermissions() {
        ApiResponse<List<Permission>> response = ApiResponse.<List<Permission>>builder()
                .status(HttpStatus.OK.value())
                .data(permissionService.getAll())
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{permission}")
    public ResponseEntity<ApiResponse<Permission>> getPermissionByName(@PathVariable("permission") String name) {
        ApiResponse<Permission> response = ApiResponse.<Permission>builder()
                .status(HttpStatus.OK.value())
                .data(permissionService.getByName(name))
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{permission}")
    public ResponseEntity<ApiResponse<Permission>> updatePermission(
            @PathVariable("permission") String name, @RequestBody @Valid PermissionRequest request) {
        ApiResponse<Permission> response = ApiResponse.<Permission>builder()
                .status(HttpStatus.OK.value())
                .data(permissionService.update(name, request.getDescription()))
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{permission}")
    public ResponseEntity<ApiResponse<Permission>> deletePermission(@PathVariable("permission") String name) {
        ApiResponse<Permission> response = ApiResponse.<Permission>builder()
                .status(HttpStatus.OK.value())
                .data(permissionService.remove(name))
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
