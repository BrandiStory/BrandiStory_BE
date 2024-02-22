package com.supercoding.brandiStory.repository.entity.orders;

import com.supercoding.brandiStory.repository.entity.OrdersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdersJpaRepository extends JpaRepository<OrdersEntity, Integer> {

}
