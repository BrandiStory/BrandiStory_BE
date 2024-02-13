package com.supercoding.brandiStory.repository.Entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Getter
@Entity
@Table(name="products")
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "product_id")
    private Integer productId;
    @Column(name = "product_name", length =255)
    private String productName;
    @Column(name= "price")
    private Integer price;
    @Column(name= "quantity")
    private Integer quantity;
    @Column(name = "company_name", length =255)
    private String companyName;

    @OneToMany(mappedBy = "productEntity", fetch = FetchType.LAZY)
    private List<ImageEntity> imageList; /*= new ArrayList<>();*/
}
