package com.supercoding.brandiStory.service;

import com.supercoding.brandiStory.repository.products.ProductJpaRepository;
import com.supercoding.brandiStory.service.exceptions.InvalidValueException;
import com.supercoding.brandiStory.web.dto.CartItemDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class CartService {
    private List<CartItemDTO> cartItemList;

    public CartService() {
        this.cartItemList = new ArrayList<>();
    }

    public void addToCart(CartItemDTO cartItem) {
        cartItemList.add(cartItem);
    }

    public List<CartItemDTO> getCartItems() {
        return cartItemList;
    }

    //수량만 변경할 수 있는 코드
    public void updateCart(Integer productId, Integer newQuantity){
        for(CartItemDTO cartItemDTO:cartItemList){
            if(cartItemDTO.getProductId().equals(productId)){
                cartItemDTO.setQuantity(newQuantity);
                cartItemDTO.setTotalPrice(cartItemDTO.getPrice()*newQuantity);
                return;
            }
        }
        // 해당 상품이 장바구니에 없는 경우 예외 처리 또는 에러 메시지 반환
        throw new InvalidValueException("상품이 장바구니에 존재하지 않습니다.");
    }
}
