package com.api.demo.model;

import java.util.Set;

import jakarta.persistence.*;

import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "users")
public class User extends BaseModel {
    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "firstname")
    private String firstname;

    @Column(name = "lastname")
    private String lastname;

    @ManyToMany
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role"))
    private Set<Role> roles;

    //    @Override
    //    public Collection<? extends GrantedAuthority> getAuthorities() {
    //        return Collections.singleton(new SimpleGrantedAuthority("USER"));
    //    }
    //
    //    @Override
    //    public boolean isAccountNonExpired() {
    //        return true;
    //    }
    //
    //    @Override
    //    public boolean isAccountNonLocked() {
    //        return true;
    //    }
    //
    //    @Override
    //    public boolean isCredentialsNonExpired() {
    //        return true;
    //    }
    //
    //    @Override
    //    public boolean isEnabled() {
    //        return true;
    //    }
}
