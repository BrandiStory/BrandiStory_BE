package com.supercoding.brandiStory.service;

import com.supercoding.brandiStory.web.dto.CartItemDTO;
import com.supercoding.brandiStory.web.dto.OrderDTO;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    public void placeOrder(OrderDTO orderDTO) {
        System.out.println("주문을 진행할 유저 아이디" + orderDTO.getUserId());
        System.out.println("배송주소: " + orderDTO.getShippingAddress());
        for (CartItemDTO cartItem : orderDTO.getCartItemDTOList()) {
            System.out.println("제품이름: " + cartItem.getProductName() + ",수량: " + cartItem.getQuantity());
        }
        System.out.println("주문이 성공적으로 완료되었습니다!");
    }
}
