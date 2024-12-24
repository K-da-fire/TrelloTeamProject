package com.example.trelloteamproject.member.entity;

import com.example.trelloteamproject.common.BaseEntity;
import com.example.trelloteamproject.common.Auth;
import com.example.trelloteamproject.util.PasswordEncoder;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, length = 100)
    private String email;

    @Convert(converter = PasswordEncoder.class)
    @Column(length = 100)
    private String password;

    @Column(length = 10)
    private String name;

    @Enumerated(value = EnumType.STRING)
    private Auth auth;

    @JsonFormat(pattern = "yy:MM:dd hh:mm:ss")
    private LocalDateTime deletedAt;

    public User(String email, String password, String name, Auth auth) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.auth = auth;
    }

    public void delete(){
        this.deletedAt = LocalDateTime.now();
    }
}
