package com.supercoding.brandiStory.service;

import com.supercoding.brandiStory.repository.carts.CartItemJpaRepository;
import com.supercoding.brandiStory.repository.entity.CartItemEntity;
import com.supercoding.brandiStory.repository.entity.ProductEntity;
import com.supercoding.brandiStory.repository.products.ProductJpaRepository;
import com.supercoding.brandiStory.service.exceptions.InvalidValueException;
import com.supercoding.brandiStory.service.exceptions.NotAcceptException;
import com.supercoding.brandiStory.service.exceptions.NotFoundException;
import com.supercoding.brandiStory.service.mapper.CartMapper;
import com.supercoding.brandiStory.web.dto.CartItemDTO;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Slf4j
@Service
public class CartService {
    private final CartItemJpaRepository cartItemJpaRepository;
    private final ProductJpaRepository productJpaRepository;
//    private List<CartItemDTO> cartItemList;

    //    public CartService() {
//        this.cartItemList = new ArrayList<>();
//    }
    public Integer addToCart(CartItemDTO cartItemDTO) {
        CartItemEntity cartItemEntity = CartMapper.INSTANCE.cartItemDTOToCartItemEntity(cartItemDTO);
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

 //카트id하나당 제품id 하나씩 들어가있는데 리스트가 들어가게끔 해보기
    //productId를 가져오면 productPrice를 같이 가져오도록 코드짜보기 2/15
    public CartItemDTO updateCartItemDTO(String id, CartItemDTO cartItemDTO) {
        Integer cartIdInt = Integer.valueOf(id);
        CartItemEntity cartItemEntity = cartItemJpaRepository.findById(cartIdInt)
                .orElseThrow(() -> new RuntimeException("장바구니를 찾을 수 없습니다"));

       BeanUtils.copyProperties(cartItemDTO, cartItemEntity, "id");
//        cartItemEntity =CartMapper.INSTANCE.cartItemDTOToCartItemEntity(cartItemDTO);
        CartItemEntity updatedCartItem = cartItemJpaRepository.save(cartItemEntity);
        CartItemDTO updatedCartItemDTO = new CartItemDTO();

      BeanUtils.copyProperties(updatedCartItem, updatedCartItemDTO);
//        updatedCartItemDTO = CartMapper.INSTANCE.cartItemEntitytoCartItemDTO(updatedCartItem);// updatedCartItemDTO.setCartItemsId(cartIdInt);
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
