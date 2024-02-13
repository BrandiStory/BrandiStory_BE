package com.supercoding.brandiStory.service.mapper;

import com.supercoding.brandiStory.web.dto.CartItemDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OrderMapper {
    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

   // @Mapping(target = "userId", ignore = true) // userId는 클라이언트로부터 받아오므로 매핑하지 않음
   // @Mapping(target = "shippingAddress", source = "cartItemDTO.shippingAddress")//쉬핑어떻게 매핑하지.. 고민해보기
   // OrderDTO cartItemDTOtoOrderDTO(CartItemDTO cartItemDTO);
}