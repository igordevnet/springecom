package com.firstproject.springecom.model.dto;

public record OrderItemRequest (
    Long productId,
    int quantity
    ) {}
