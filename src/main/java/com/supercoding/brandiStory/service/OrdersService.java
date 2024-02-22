package com.supercoding.brandiStory.service;

import com.supercoding.brandiStory.repository.carts.CartItemJpaRepository;
import com.supercoding.brandiStory.repository.entity.CartItemEntity;
import com.supercoding.brandiStory.repository.entity.OrdersEntity;
import com.supercoding.brandiStory.repository.entity.UserEntity;
import com.supercoding.brandiStory.repository.orders.OrdersJpaRepository;
import com.supercoding.brandiStory.repository.users.UserJpaRepository;
import com.supercoding.brandiStory.service.mapper.OrdersMapper;
import com.supercoding.brandiStory.web.dto.OrderListReqDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;

@Slf4j
@Service
@Transactional
public class OrdersService {

    private final OrdersJpaRepository ordersRepository;
    private final CartItemJpaRepository cartItemRepository;
    private final UserJpaRepository userRepository;
    private final OrdersMapper ordersMapper;

    @Autowired
    public OrdersService(OrdersJpaRepository ordersRepository, CartItemJpaRepository cartItemRepository,
                         UserJpaRepository userRepository, OrdersMapper ordersMapper) {
        this.ordersRepository = ordersRepository;
        this.cartItemRepository = cartItemRepository;
        this.userRepository = userRepository;
        this.ordersMapper = ordersMapper;
    }

    public OrdersEntity createOrder(OrderListReqDto orderDTO) {
        CartItemEntity cartItem = cartItemRepository.findById(orderDTO.getCartItemsId())
                .orElseThrow(() -> new RuntimeException("장바구니 항목을 찾을 수 없습니다."));
        UserEntity user = userRepository.findById(orderDTO.getUsersId())
                .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));
        OrdersEntity order = ordersMapper.orderDtoToEntity(orderDTO);
        order.setCartItemsId(cartItem);
        order.setUserEntity(user);
        if (order.getDeliveryAddress() == null || order.getDeliveryAddress().isEmpty()) {
            order.setDeliveryAddress(user.getAddress());
        }
        order.setOrderDate(LocalDateTime.now());

        return ordersRepository.save(order);
    }

    public OrdersEntity getOrderById(Integer id) {
        return ordersRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));
    }
}
