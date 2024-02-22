package com.supercoding.brandiStory.repository.users;

import com.supercoding.brandiStory.repository.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserJpaRepository extends JpaRepository<UserEntity, Integer> {
    Optional<UserEntity> findByEmail(String email);

    // FETCH JOIN 사용하여 N+1 문제 방지
    @Query("SELECT ue FROM UserEntity ue JOIN FETCH ue.userRoles upr JOIN FETCH upr.roles WHERE ue.email = :email")
    Optional<UserEntity> findByEmailFetchJoin(String email);
    boolean existsByEmail(String email);
    
}
