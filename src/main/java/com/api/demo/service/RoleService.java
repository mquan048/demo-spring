package com.api.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.api.demo.dto.request.RoleRequest;
import com.api.demo.exception.AppException;
import com.api.demo.exception.NotFoundException;
import com.api.demo.model.Role;
import com.api.demo.repository.RoleRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;

    public Role create(RoleRequest request) {
        if (roleRepository.existsByName(request.getName())) throw new AppException("Role has existed");

        Role role = Role.builder()
                .name(request.getName())
                .description(request.getDescription())
                .build();

        return roleRepository.save(role);
    }

    public List<Role> getAll() {
        return roleRepository.findAll();
    }

    public Role getByName(String name) {
        return roleRepository.findByName(name).orElseThrow(NotFoundException::new);
    }

    public Role update(String name, String newDescription) {
        Role role = this.getByName(name);
        role.setDescription(newDescription);
        return roleRepository.save(role);
    }

    public Role remove(String name) {
        Role role = this.getByName(name);
        roleRepository.delete(role);
        return role;
    }
}
