package com.firstproject.springecom.controller;

import com.firstproject.springecom.model.dto.OrderRequest;
import com.firstproject.springecom.model.dto.OrderResponse;
import com.firstproject.springecom.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
@CrossOrigin
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/orders/place")
    public ResponseEntity<OrderResponse> placeOrder(
            @RequestBody OrderRequest orderRequest
    ) {
        OrderResponse orderResponse = orderService.placeOrder(orderRequest);
        return new ResponseEntity<>(orderResponse, HttpStatus.CREATED);
    }

    @GetMapping("/orders")
    public ResponseEntity<List<OrderResponse>> getAllOrders() {
        List<OrderResponse> orderResponse = orderService.getAllOrders();
        return new ResponseEntity<>(orderResponse, HttpStatus.OK);
    }
}
