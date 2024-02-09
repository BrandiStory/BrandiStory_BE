package com.supercoding.brandiStory.service.mapper;

import com.supercoding.brandiStory.repository.Entity.ProductEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    ProductEntity productEntityToProductDTO(ProductEntity productEntity);
}
