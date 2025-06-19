package com.api.demo.configuration;

import java.util.HashSet;
import java.util.Set;

import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.api.demo.exception.NotFoundException;
import com.api.demo.model.Permission;
import com.api.demo.model.Role;
import com.api.demo.model.User;
import com.api.demo.repository.PermissionRepository;
import com.api.demo.repository.RoleRepository;
import com.api.demo.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class ApplicationConfig {

    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final PermissionRepository permissionRepository;

    @Bean
    public ApplicationRunner applicationRunner() {
        return args -> {
            User user = createAdminUser(createAdminRole(createCreateDataPermission()));
            log.info(user.toString());
        };
    }

    public User createAdminUser(Role adminRole) {
        if (!userRepository.existsByUsername("admin")) {
            Set<Role> roles = new HashSet<>();
            roles.add(adminRole);

            User user = User.builder()
                    .username("admin")
                    .password(passwordEncoder.encode("admin"))
                    .roles(roles)
                    .build();
            return userRepository.save(user);
        } else {
            return userRepository.findByUsername("admin").orElseThrow(NotFoundException::new);
        }
    }

    public Role createAdminRole(Permission createDatePermission) {
        if (!roleRepository.existsByName("ADMIN")) {
            Set<Permission> permissions = new HashSet<>();
            permissions.add(createDatePermission);

            Role role = Role.builder()
                    .name("ADMIN")
                    .description("Admin role")
                    .permissions(permissions)
                    .build();
            return roleRepository.save(role);
        } else {
            return roleRepository.findByName("ADMIN").orElseThrow(NotFoundException::new);
        }
    }

    public Permission createCreateDataPermission() {
        if (!permissionRepository.existsByName("CREATE_DATA")) {
            Permission permission = Permission.builder()
                    .name("CREATE_DATA")
                    .description("Create data permission")
                    .build();
            return permissionRepository.save(permission);
        } else {
            return permissionRepository.findByName("CREATE_DATA").orElseThrow(NotFoundException::new);
        }
    }
}
