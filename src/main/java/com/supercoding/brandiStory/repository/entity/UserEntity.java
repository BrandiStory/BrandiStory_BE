package com.supercoding.brandiStory.repository.entity;

import com.supercoding.brandiStory.repository.entity.enums.SexType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class UserEntity {
// <<<<<<< product1
    @Id @Column(name="users_id") @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer usersId;
// ==================
//     @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
//     @Column(name = "users_id")
//     private Integer id;
// >>>>>>> development
    @Column(nullable = false, unique = true)
    private String email;
    @Column(length = 20)
    private String username;
    @Column
    private String password;
    @Column(name = "phone", length = 20)
    private String phoneNumber;
    @Column(length = 30, columnDefinition = "TEXT")
    private String address;
    @Enumerated(EnumType.STRING)
    private SexType gender;

    @CreationTimestamp // INSERT 시 자동으로 값을 채워줌
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "userEntity")
    private List<CartItemEntity> cartItemEntity;
}
