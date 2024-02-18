package com.supercoding.brandiStory.repository.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="cart_items")
public class CartItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "cart_items_id")
    private Integer cartItemsId;

//    //OneToOne? or ManyToOne?
//    @ManyToOne(fetch=FetchType.LAZY)
//    @JoinColumn(name="users_id")
//    private UserEntity userEntity;
//
    @Column(name = "users_id")
    private Integer usersId;

    @Column(name = "product_id")
    private Integer productId;
//    @OneToMany(mappedBy = "CartItemEntity",  fetch = FetchType.LAZY)
//    private Set<CartItemEntity> cartItemEntities;
    @Column(name = "price")
    private Integer price;
    @Column(name = "quantity")
    private Integer quantity;
    @Column(name = "total_price")
    private Integer totalPrice;
//
//    @OneToMany(mappedBy = "CartItemEntity",  fetch = FetchType.LAZY)
//    private List<ProductEntity> productEntities;

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

