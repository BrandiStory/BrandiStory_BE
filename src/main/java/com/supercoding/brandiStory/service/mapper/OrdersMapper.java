package com.supercoding.brandiStory.service.mapper;

import com.supercoding.brandiStory.repository.carts.CartItemJpaRepository;
import com.supercoding.brandiStory.repository.entity.OrdersEntity;
import com.supercoding.brandiStory.web.dto.OrderListReqDto;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface OrdersMapper {

    OrdersMapper INSTANCE = Mappers.getMapper(OrdersMapper.class);

    @Mapping(target = "cartItemsId", ignore = true)
    @Mapping(source = "customerName", target = "customerName")
    @Mapping(source = "customerPhone", target = "customerPhone")
    @Mapping(source = "customerEmail", target = "customerEmail")
    @Mapping(source = "deliveryAddress", target = "deliveryAddress")
    OrdersEntity orderDtoToEntity(OrderListReqDto orderDTO);
    @AfterMapping
    default void mapCartItemsId(@MappingTarget OrdersEntity order, OrderListReqDto orderDTO, @Context CartItemJpaRepository cartItemRepository) {
        if (orderDTO.getCart_items_id() != null) {
            order.setCartItemsId(cartItemRepository.findById(orderDTO.getCart_items_id()).orElse(null));
        }
    }
}
