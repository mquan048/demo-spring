package com.api.demo.controller;

import java.util.List;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.api.demo.dto.request.RoleRequest;
import com.api.demo.dto.response.ApiResponse;
import com.api.demo.model.Role;
import com.api.demo.service.RoleService;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/role")
@RequiredArgsConstructor
@Tag(name = "Role", description = "Role management APIs")
public class RoleController {
    private final RoleService roleService;

    @PostMapping
    public ResponseEntity<ApiResponse<Role>> createRole(@RequestBody @Valid RoleRequest request) {
        Role role = roleService.create(request);
        ApiResponse<Role> response = ApiResponse.<Role>builder()
                .status(HttpStatus.CREATED.value())
                .data(role)
                .build();
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Role>>> getRoles() {
        ApiResponse<List<Role>> response = ApiResponse.<List<Role>>builder()
                .status(HttpStatus.OK.value())
                .data(roleService.getAll())
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{role}")
    public ResponseEntity<ApiResponse<Role>> getRoleByName(@PathVariable("role") String name) {
        ApiResponse<Role> response = ApiResponse.<Role>builder()
                .status(HttpStatus.OK.value())
                .data(roleService.getByName(name))
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{role}")
    public ResponseEntity<ApiResponse<Role>> updateRole(
            @PathVariable("role") String name, @RequestBody @Valid RoleRequest request) {
        ApiResponse<Role> response = ApiResponse.<Role>builder()
                .status(HttpStatus.OK.value())
                .data(roleService.update(name, request.getDescription()))
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{role}")
    public ResponseEntity<ApiResponse<Role>> deleteRole(@PathVariable("role") String name) {
        ApiResponse<Role> response = ApiResponse.<Role>builder()
                .status(HttpStatus.OK.value())
                .data(roleService.remove(name))
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
