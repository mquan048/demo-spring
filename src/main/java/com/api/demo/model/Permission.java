package com.api.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "permissions")
public class Permission {
    @Id
    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;
}
