package com.supercoding.brandiStory.service;

import com.supercoding.brandiStory.repository.products.ProductJpaRepository;
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
}
