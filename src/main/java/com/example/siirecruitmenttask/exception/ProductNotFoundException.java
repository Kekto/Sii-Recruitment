package com.example.siirecruitmenttask.exception;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ProductNotFoundException extends Exception {
    private final Long productId;
    @Override
    public String getMessage() {
        return "Product with provided id: " + productId + " not found!";
    }
}
