package com.supercoding.brandiStory.service.mapper;

import com.supercoding.brandiStory.repository.entity.CartItemEntity;
import com.supercoding.brandiStory.repository.entity.ProductEntity;
import com.supercoding.brandiStory.web.dto.CartItemDTO;
import com.supercoding.brandiStory.web.dto.ProductDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CartMapper {
    CartMapper INSTANCE = Mappers.getMapper(CartMapper.class);

    @Mapping(target = "cartItemsId", ignore = true)
    CartItemEntity cartItemDTOToCartItemEntity(CartItemDTO cartItemDTO);

    @Mapping(target = "cartItemsId", ignore = true)
    CartItemDTO cartItemEntitytoCartItemDTO(CartItemEntity cartItemEntity);

}