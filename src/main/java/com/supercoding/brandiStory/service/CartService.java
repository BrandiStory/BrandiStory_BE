package com.supercoding.brandiStory.service;

import com.supercoding.brandiStory.repository.carts.CartItemJpaRepository;
import com.supercoding.brandiStory.repository.entity.CartItemEntity;
import com.supercoding.brandiStory.repository.entity.ProductEntity;
import com.supercoding.brandiStory.repository.products.ProductJpaRepository;
import com.supercoding.brandiStory.service.exceptions.NotAcceptException;
import com.supercoding.brandiStory.service.exceptions.NotFoundException;
import com.supercoding.brandiStory.service.mapper.CartMapper;
import com.supercoding.brandiStory.web.dto.CartItemBody;
import com.supercoding.brandiStory.web.dto.CartItemDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Slf4j
@Service
public class CartService {
    private final CartItemJpaRepository cartItemJpaRepository;
    private final ProductJpaRepository productJpaRepository;
    public Integer addToCart(CartItemBody cartItemBody) {
        CartItemEntity cartItemEntity = CartMapper.INSTANCE.idAndCartItemBodyToCartItemEntity(null, cartItemBody);
        ProductEntity productEntity = productJpaRepository.findById(cartItemBody.getProductId())
                .orElseThrow(() -> new NotFoundException("상품을 찾을 수 없습니다."));
        cartItemEntity.setPrice(productEntity.getPrice());
        cartItemEntity.setTotalPrice(productEntity.getPrice()*cartItemBody.getQuantity());
        CartItemEntity cartItemEntityCreated;
        try {
            cartItemEntityCreated = cartItemJpaRepository.save(cartItemEntity);
        } catch (RuntimeException exception) {
            throw new NotAcceptException("Cart Item을 저장하는 도중에 Error 가 발생하였습니다.");
        }
        return cartItemEntityCreated.getCartItemsId();
    }

    public List<CartItemDTO> getCartItems() {
        List<CartItemEntity> cartItemEntities = cartItemJpaRepository.findAll();
        if (cartItemEntities.isEmpty()) throw new NotFoundException("장바구니가 비어있습니다.");

        return cartItemEntities.stream()
                .map(CartMapper.INSTANCE::cartItemEntitytoCartItemDTO)
                .collect(Collectors.toList());
    }

    public List<CartItemDTO> getCartItemsByUsersId(Integer usersId) {
        List<CartItemEntity> cartItemEntities = cartItemJpaRepository.findByUserEntityUsersId(usersId);
        if (cartItemEntities.isEmpty()) throw new NotFoundException("장바구니가 비어있습니다.");
        return cartItemEntities.stream()
                .map(CartMapper.INSTANCE::cartItemEntitytoCartItemDTO)
                .collect(Collectors.toList());
    }

    public Integer calculateTotalPrice(List<CartItemDTO> cartItems) {
        return cartItems.stream()
                .mapToInt(CartItemDTO::getTotalPrice)
                .sum();
    }

     public CartItemDTO updateCartItemDTO(String cartId, CartItemBody cartItemBody) {
        Integer cartIdInt = Integer.valueOf(cartId);
        CartItemEntity cartItemEntity = cartItemJpaRepository.findById(cartIdInt)
                .orElseThrow(() -> new RuntimeException("장바구니를 찾을 수 없습니다"));

       //BeanUtils.copyProperties(cartItemDTO, cartItemEntity, "cartItemsId");
        cartItemEntity =CartMapper.INSTANCE.idAndCartItemBodyToCartItemEntity(cartIdInt, cartItemBody);
        cartItemEntity.setCartItemsId(cartIdInt);
        CartItemEntity updatedCartItem = cartItemJpaRepository.save(cartItemEntity);
        CartItemDTO updatedCartItemDTO = new CartItemDTO();
        // BeanUtils.copyProperties(updatedCartItem, updatedCartItemDTO); BeanUtils대신 Mapper사용
   updatedCartItemDTO = CartMapper.INSTANCE.cartItemEntitytoCartItemDTO(updatedCartItem);

        return updatedCartItemDTO;
    }
}

//수량만 변경할 수 있는 코드
//    public Integer updateCart(String productId, Integer newQuantity) {
//        Integer productIdInt = Integer.valueOf(productId);
//        CartItemEntity cartItemEntity = cartItemJpaRepository.findByProductId(productIdInt);
////                .orElseThrow(()-> new NotFoundException("해당 product를 찾을 수 없습니다."));
//
//        for (CartItemDTO cartItemDTO : cartItemList) {
//            if (cartItemDTO.getProductId().equals(productIdInt)) {
//                cartItemEntity.setQuantity(newQuantity);
//                cartItemEntity.setTotalPrice(cartItemEntity.getPrice() * newQuantity);
//                return cartItemEntity.getCartItemsId();
//            }
//            throw new InvalidValueException("상품이 장바구니에 존재하지 않습니다.");
//        }
//        return cartItemEntity.getTotalPrice();
//    }
