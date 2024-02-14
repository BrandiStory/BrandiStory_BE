//package com.supercoding.brandiStory.repository.entity;
//
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//import java.util.Optional;
//
//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
//@Entity
//@Table(name="product_images")
//public class ImageEntity {
//    @Id @Column(name = "image_id")
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Integer imageId;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "product_id")
//    private ProductEntity productEntity;
//
//    @Column(name = "image")
//    private Integer image;
//    @Column(name = "sequence")
//    private Integer sequence;
//
//   // public Optional<ProductEntity> getProductEntity() {
////        return Optional.ofNullable(productEntity);
////    }
//
//}