package com.supercoding.brandiStory.service.mapper;

import com.supercoding.brandiStory.repository.Entity.ProductEntity;
import com.supercoding.brandiStory.web.dto.ProductDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    ProductDTO productEntityToProductDTO(ProductEntity productEntity);
}
