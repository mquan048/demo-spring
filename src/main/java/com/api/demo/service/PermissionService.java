package com.api.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.api.demo.dto.request.PermissionRequest;
import com.api.demo.exception.AppException;
import com.api.demo.exception.NotFoundException;
import com.api.demo.model.Permission;
import com.api.demo.repository.PermissionRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PermissionService {

    private final PermissionRepository permissionRepository;

    public Permission create(PermissionRequest request) {
        if (permissionRepository.existsByName(request.getName())) throw new AppException("Permission has existed");

        Permission permission = Permission.builder()
                .name(request.getName())
                .description(request.getDescription())
                .build();

        return permissionRepository.save(permission);
    }

    public List<Permission> getAll() {
        return permissionRepository.findAll();
    }

    public Permission getByName(String name) {
        return permissionRepository.findByName(name).orElseThrow(NotFoundException::new);
    }

    public Permission update(String name, String newDescription) {
        Permission permission = this.getByName(name);
        permission.setDescription(newDescription);
        return permissionRepository.save(permission);
    }

    public Permission remove(String name) {
        Permission permission = this.getByName(name);
        permissionRepository.delete(permission);
        return permission;
    }
}
