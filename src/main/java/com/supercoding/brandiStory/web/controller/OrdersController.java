package com.supercoding.brandiStory.web.controller;

import com.supercoding.brandiStory.repository.entity.OrdersEntity;
import com.supercoding.brandiStory.service.OrdersService;
import com.supercoding.brandiStory.web.dto.OrderListReqDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;

import java.util.Map;
@RestController
@Slf4j
@RequestMapping("/orders")
public class OrdersController {

    private final OrdersService ordersService;

    @Autowired
    public OrdersController(OrdersService ordersService) {
        this.ordersService = ordersService;
    }
    @Operation(summary = "주문 조회")
    @PostMapping
    public ResponseEntity<?> createOrder(@RequestBody OrderListReqDto orderDTO) {
        try {
            OrdersEntity newOrder = ordersService.createOrder(orderDTO);
            return new ResponseEntity<>(newOrder, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @Operation(summary = "주문 하기")
    @GetMapping("/{id}")
    public ResponseEntity<?> getOrderById(@PathVariable Integer id) {
        try {
            OrdersEntity order = ordersService.getOrderById(id);
            return ResponseEntity.ok(order);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}