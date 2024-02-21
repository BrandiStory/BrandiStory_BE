package com.supercoding.brandiStory.repository.carts;

import com.supercoding.brandiStory.repository.entity.CartItemEntity;
import com.supercoding.brandiStory.repository.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartItemJpaRepository extends JpaRepository<CartItemEntity, Integer> {

    List<CartItemEntity> findByUserEntityUsersId(Integer usersIdInt);
    @Query("SELECT DISTINCT c FROM CartItemEntity c WHERE c.productEntity.productId = :productId")
    List<CartItemEntity> findByProductId(@Param("productId") Integer productId);

    CartItemEntity findByProductId(Integer productIdInt);

    Optional<CartItemEntity> findByCartItemsIdAndProductId(Integer cartIdInt, Integer productId);

}
