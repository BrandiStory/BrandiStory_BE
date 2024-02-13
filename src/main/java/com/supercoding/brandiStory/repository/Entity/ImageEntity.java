package com.supercoding.brandiStory.repository.Entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.Optional;

@Getter
@Entity
@Table(name="product_images")
public class ImageEntity {
    @Id @Column(name = "image_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer imageId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private ProductEntity productEntity;

    @Column(name = "image")
    private Integer image;
    @Column(name = "sequence")
    private Integer sequence;

    public Optional<ProductEntity> getProductEntity() {
        return Optional.ofNullable(productEntity);
    }

}