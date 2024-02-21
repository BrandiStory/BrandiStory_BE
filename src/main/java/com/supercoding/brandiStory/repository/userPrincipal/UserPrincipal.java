package com.supercoding.brandiStory.repository.userPrincipal;

import com.supercoding.brandiStory.repository.entity.UserEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_principal")
public class UserPrincipal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_principal_id")
    private Integer userPrincipalId;

    @OneToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @Column(nullable = false, unique = true)
    private String email;

    @Column
    private String password;

    @OneToMany(mappedBy = "userPrincipal")
    private Collection<UserPricipalRoles> userPricipalRoles;
}
