package com.supercoding.brandiStory.repository.users;

import com.supercoding.brandiStory.repository.users.enums.SexType;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
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
    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+.[A-Za-z]{2,6}$", message = "이메일 형식에 맞지 않습니다.")
    private String email;
    @Column(length = 20)
    private String username;
    @Column(length = 20)
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,20}$", message = "비밀번호는 영문자와 숫자의 조합으로 8자 이상 20자 이하여야 합니다.")
    private String password;
    @Column(name = "phone", length = 20)
    private String phoneNumber;
    @Column(length = 30, columnDefinition = "TEXT")
    private String address;
    @Enumerated(EnumType.STRING)
    private SexType gender;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
