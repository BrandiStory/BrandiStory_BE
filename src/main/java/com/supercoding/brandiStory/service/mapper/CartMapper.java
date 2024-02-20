package com.supercoding.brandiStory.service.mapper;

import com.supercoding.brandiStory.repository.entity.CartItemEntity;
import com.supercoding.brandiStory.repository.entity.ProductEntity;
import com.supercoding.brandiStory.web.dto.CartItemBody;
import com.supercoding.brandiStory.web.dto.CartItemDTO;
import com.supercoding.brandiStory.web.dto.ProductDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CartMapper {
    CartMapper INSTANCE = Mappers.getMapper(CartMapper.class);

    @Mapping(target="productEntity.price", source = "cartItemBody.price")
    @Mapping(target="userEntity.usersId", source = "cartItemBody.usersId")
    @Mapping(target="productEntity.productId", source = "cartItemBody.productId")
    CartItemEntity idAndCartItemBodyToCartItemEntity(Integer id, CartItemBody cartItemBody);

    @Mapping(target="price", source = "productEntity.price")
    @Mapping(target="usersId", source = "userEntity.usersId")
    @Mapping(target="productId", source = "productEntity.productId")
    @Mapping(target = "totalPrice", expression = "java(calculateTotalPrice(cartItemEntity))")
    CartItemDTO cartItemEntitytoCartItemDTO(CartItemEntity cartItemEntity);

    default Integer calculateTotalPrice(CartItemEntity cartItemEntity) {
        return cartItemEntity.getProductEntity().getPrice() * cartItemEntity.getQuantity();
    }
}