package com.api.demo.service;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.api.demo.dto.request.UserCreateRequest;
import com.api.demo.dto.request.UserUpdateRequest;
import com.api.demo.dto.response.UserResponse;
import com.api.demo.exception.NotFoundException;
import com.api.demo.mapper.UserMapper;
import com.api.demo.model.User;
import com.api.demo.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    private final PasswordEncoder passwordEncoder;

    public UserResponse create(UserCreateRequest request) {
        User user = userMapper.toUser(request);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        //        HashSet<String> roles = new HashSet<>();
        //        roles.add(Roles.USER.name());
        ////       user.setRoles(roles);

        return userMapper.toUserResponse(userRepository.save(user));
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public User getById(String id) {
        return userRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    public User update(String id, UserUpdateRequest request) {
        User user = this.getById(id);
        userMapper.updateUser(user, request);
        return userRepository.save(user);
    }

    public User remove(String id) {
        User user = this.getById(id);
        userRepository.delete(user);
        return user;
    }
}
