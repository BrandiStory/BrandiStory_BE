package com.supercoding.brandiStory.repository.users;

import com.supercoding.brandiStory.repository.users.enums.SexType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class UserEntity {
    @Id @Column @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(length = 20)
    private String username;
    @Column(length = 20)
    private String password;
    @Column(name = "phone", length = 20)
    private String phoneNumber;
    @Column(length = 30, columnDefinition = "TEXT")
    private String address;
    @Enumerated(EnumType.STRING)
    private SexType sex;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
