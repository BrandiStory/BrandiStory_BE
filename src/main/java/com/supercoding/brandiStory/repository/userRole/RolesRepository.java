package com.supercoding.brandiStory.repository.userRole;

import com.supercoding.brandiStory.repository.entity.role.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RolesRepository extends JpaRepository<Roles, Integer> {

    Optional<Roles> findByName(String name);
}
