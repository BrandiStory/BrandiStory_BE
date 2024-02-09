package com.supercoding.brandiStory.repository.products;

import com.supercoding.brandiStory.repository.Entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductJpaRepository extends JpaRepository<ProductEntity, Integer> {

}
