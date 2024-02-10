package com.supercoding.brandiStory.repository.Entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.Optional;

@Getter
@Entity
@Table(name="productImages")
public class ImageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer imageId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private ProductEntity productEntity;
    //    @Column(name = "product_id")
//    private Integer productId;
    @Column(name = "image")
    private Integer image;
    @Column(name = "sequence")
    private Integer sequence;

    public Optional<ProductEntity> getProductEntity() {
        return Optional.ofNullable(productEntity);
    }

}