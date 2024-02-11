package com.supercoding.brandiStory.repository.users;

import com.supercoding.brandiStory.web.dto.SignUp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserAuthRepository extends JpaRepository<SignUp, Integer> {

    boolean existsByEmail(String email);
}
