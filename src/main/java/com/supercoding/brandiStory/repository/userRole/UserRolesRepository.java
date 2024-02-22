package com.supercoding.brandiStory.repository.userRole;

import com.supercoding.brandiStory.repository.entity.role.UserRoles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRolesRepository extends JpaRepository<UserRoles, Integer> {

}
