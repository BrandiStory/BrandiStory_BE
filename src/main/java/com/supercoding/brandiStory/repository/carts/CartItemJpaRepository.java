package com.supercoding.brandiStory.repository.carts;

import com.supercoding.brandiStory.repository.entity.CartItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartItemJpaRepository extends JpaRepository<CartItemEntity, Integer> {
    CartItemEntity findByProductId(Integer productIdInt);
}
