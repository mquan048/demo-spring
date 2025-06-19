package com.api.demo.model;

import java.util.Date;

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
@Table(name = "invalidated_tokens")
public class InvalidatedToken {
    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "expire_time")
    private Date expireTime;
}
