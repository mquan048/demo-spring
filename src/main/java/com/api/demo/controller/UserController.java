package com.api.demo.controller;

import java.util.List;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.api.demo.dto.request.UserCreateRequest;
import com.api.demo.dto.request.UserUpdateRequest;
import com.api.demo.dto.response.ApiResponse;
import com.api.demo.dto.response.UserResponse;
import com.api.demo.model.User;
import com.api.demo.service.UserService;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Tag(name = "User", description = "User management APIs")
@SecurityRequirement(name = "bearerAuth")
public class UserController {
    private final UserService userService;

    @PostMapping
    public ResponseEntity<ApiResponse<UserResponse>> createUser(@RequestBody @Valid UserCreateRequest request) {
        UserResponse user = userService.create(request);
        ApiResponse<UserResponse> response = ApiResponse.<UserResponse>builder()
                .status(HttpStatus.CREATED.value())
                .data(user)
                .build();
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<User>>> getUsers() {

        //        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //        log.info(authentication.getName());
        //        authentication.getAuthorities().forEach(grantedAuthority ->
        // log.info(grantedAuthority.getAuthority()));
        ApiResponse<List<User>> response = ApiResponse.<List<User>>builder()
                .status(HttpStatus.OK.value())
                .data(userService.getAll())
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<ApiResponse<User>> getUserById(@PathVariable("userId") String userId) {
        ApiResponse<User> response = ApiResponse.<User>builder()
                .status(HttpStatus.OK.value())
                .data(userService.getById(userId))
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<ApiResponse<User>> updateUser(
            @PathVariable("userId") String userId, @RequestBody @Valid UserUpdateRequest request) {
        ApiResponse<User> response = ApiResponse.<User>builder()
                .status(HttpStatus.OK.value())
                .data(userService.update(userId, request))
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponse<User>> deleteUser(@PathVariable("userId") String userId) {
        ApiResponse<User> response = ApiResponse.<User>builder()
                .status(HttpStatus.OK.value())
                .data(userService.remove(userId))
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
