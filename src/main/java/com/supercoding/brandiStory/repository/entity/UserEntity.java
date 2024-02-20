package com.supercoding.brandiStory.repository.entity;

import com.supercoding.brandiStory.repository.entity.enums.SexType;
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
    private Integer usersId;
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
    private SexType gender;
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @OneToOne(mappedBy = "userEntity")
    private CartItemEntity cartItemEntity;
}