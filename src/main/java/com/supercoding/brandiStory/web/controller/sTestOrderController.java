//package com.supercoding.brandiStory.web.controller;
//
//import com.supercoding.brandiStory.service.OrderService;
//import com.supercoding.brandiStory.service.mapper.OrderMapper;
//import com.supercoding.brandiStory.web.dto.CartItemDTO;
//import com.supercoding.brandiStory.web.dto.OrderDTO;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.coyote.Response;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequiredArgsConstructor
//@Slf4j
//public class OrderController {
//
//    private final OrderService orderService;
//
//    @PostMapping("/order")
//    public ResponseEntity<String> placeOrder(@RequestBody CartItemDTO cartItemDTO){
//        try {
//            OrderDTO orderDTO = OrderMapper.INSTANCE.cartItemDTOtoOrderDTO(cartItemDTO);
//            orderService.placeOrder(orderDTO);
//            return ResponseEntity.ok("주문이 성공적으로 완료됐습니다!");
//        }catch(RuntimeException e){
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("주문 처리 중 오류가 발생했습니다.");
//        }
//
//    }
//}
