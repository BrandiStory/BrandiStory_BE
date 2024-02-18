package com.supercoding.brandiStory.service.mapper;

import com.supercoding.brandiStory.repository.entity.ImageEntity;
import com.supercoding.brandiStory.repository.entity.ProductEntity;
import com.supercoding.brandiStory.web.dto.ImageDTO;
import com.supercoding.brandiStory.web.dto.ProductDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    @Mapping(source = "imageList", target = "imageList")
    ProductDTO productEntityToProductDTO(ProductEntity productEntity);

    @Mapping(source = "imageEntity.productEntity.productId", target = "productId")
    ImageDTO imageEntityToImageDTO(ImageEntity imageEntity);
}

