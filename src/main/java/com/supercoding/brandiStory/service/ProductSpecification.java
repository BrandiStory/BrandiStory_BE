package com.supercoding.brandiStory.service;

import com.supercoding.brandiStory.repository.entity.ImageEntity;
import org.springframework.data.jpa.domain.Specification;

public class ProductSpecification {
    public static Specification<ImageEntity> hasSequence(int sequence) {
        return (root, query, builder) -> builder.equal(root.get("imageList").get("sequence"), sequence);
    }
}
