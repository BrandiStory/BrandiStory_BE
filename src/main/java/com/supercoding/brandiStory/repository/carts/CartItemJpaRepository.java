package com.supercoding.brandiStory.repository.carts;

import com.supercoding.brandiStory.repository.entity.CartItemEntity;
import com.supercoding.brandiStory.repository.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartItemJpaRepository extends JpaRepository<CartItemEntity, Integer> {
    List<CartItemEntity> findByUserEntityUsersId(Integer usersIdInt);

}
