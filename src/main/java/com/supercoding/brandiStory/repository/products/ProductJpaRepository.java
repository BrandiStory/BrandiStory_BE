package com.supercoding.brandiStory.repository.products;

import com.supercoding.brandiStory.repository.entity.ProductEntity;
import com.supercoding.brandiStory.web.dto.ProductDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductJpaRepository extends JpaRepository<ProductEntity, Integer> {
    @Query("SELECT p FROM ProductEntity p LEFT JOIN FETCH p.imageList")
    List<ProductEntity> findAllWithImages();

//    @Query("SELECT p FROM ProductEntity p LEFT JOIN FETCH p.imageList WHERE p.productId = :productId")
//    Optional<ProductEntity> findByIdWithImages(@Param("productId") Integer productId);
}

