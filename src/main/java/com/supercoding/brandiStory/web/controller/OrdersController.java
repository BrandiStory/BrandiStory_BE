package com.supercoding.brandiStory.web.controller;

import com.supercoding.brandiStory.repository.entity.OrdersEntity;
import com.supercoding.brandiStory.service.OrdersService;
import com.supercoding.brandiStory.web.dto.OrderListReqDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/orders")
public class OrdersController {

    private final OrdersService ordersService;

    @Autowired
    public OrdersController(OrdersService ordersService) {
        this.ordersService = ordersService;
    }

    @PostMapping("")
    public ResponseEntity<?> createOrder(@RequestBody OrderListReqDto orderDTO) {
        System.out.println("orderDTO >>> "+ orderDTO);
        try {
            OrdersEntity newOrder = ordersService.createOrder(orderDTO);
            System.out.println("sdfsdfs" + newOrder);
            return new ResponseEntity<>(newOrder, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            System.out.println("sdfsdfs" + e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

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
