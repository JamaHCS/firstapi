package com.jamadev.firstapi.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@EntityListeners(AuditingEntityListener.class)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private String email;

    @Getter
    @Setter
    private String password;

    @Getter
    @Setter
    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @Getter
    @Setter
    @LastModifiedDate
    private LocalDateTime updatedAt;

    @Getter
    @Setter
    @CreatedBy
    @Column(updatable = false)
    private String createdBy;

    @Getter
    @Setter
    @LastModifiedBy
    private String updatedBy;

    public User() {}

    public User(String name, String mail, String password) {
        this.name = name;
        this.email = mail;
        this.password = password;
    }
}
