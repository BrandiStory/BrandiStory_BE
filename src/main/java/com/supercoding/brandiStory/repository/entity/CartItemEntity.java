package com.supercoding.brandiStory.repository.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "cartItemsId")
@Builder
@Entity
@Table(name="cart_items")
public class CartItemEntity {
    @Id @Column(name= "cart_items_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cartItemsId;

    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="users_id")
    private UserEntity userEntity;
//    @Column(name = "users_id")
//    private Integer usersId;

//    @Column(name = "product_id")
//    private Integer productId;

    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="product_id")
    private ProductEntity productEntity;

    @Column(name = "price")
    private Integer price;
    @Column(name = "quantity")
    private Integer quantity;
    @Column(name = "total_price")
    private Integer totalPrice;

    @Transient
    public Integer getTotalPrice() {
        if (price != null && quantity != null) {
            return price * quantity;
        }
        return null;
    }

    @PrePersist
    @PreUpdate
    private void updateTotalPrice() {
        this.totalPrice = getTotalPrice();
    }
}

