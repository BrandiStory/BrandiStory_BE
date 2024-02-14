package com.supercoding.brandiStory.service.mapper;

import com.supercoding.brandiStory.repository.entity.CartItemEntity;
import com.supercoding.brandiStory.repository.entity.ProductEntity;
import com.supercoding.brandiStory.web.dto.CartItemDTO;
import com.supercoding.brandiStory.web.dto.ProductDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CartMapper {
    CartMapper INSTANCE = Mappers.getMapper(CartMapper.class);

   // @Mapping(target="cartItemId", ignore=true) //cartItemId 자동추가할지, 내가 설정해줄지 어떻게할지 생각해보기.
    CartItemEntity cartItemDTOToCartItemEntity(CartItemDTO cartItemDTO);
    CartItemDTO cartItemEntitytoCartItemDTO(CartItemEntity cartItemEntity);
}
