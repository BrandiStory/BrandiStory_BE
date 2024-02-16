package com.supercoding.brandiStory.repository.userPrincipal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserPrincipalRepository extends JpaRepository<UserPrincipal, Integer> {

    // FETCH JOIN 사용하여 N+1 문제 방지
    @Query("SELECT up FROM UserPrincipal up JOIN FETCH up.userPricipalRoles upr JOIN FETCH upr.roles WHERE up.email = :email")
    Optional<UserPrincipal> findByEmailFetchJoin(String email);

    boolean existsByEmail(String email);
}
