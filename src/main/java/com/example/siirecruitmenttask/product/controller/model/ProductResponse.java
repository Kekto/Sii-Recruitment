package com.example.siirecruitmenttask.product.controller.model;

import com.example.siirecruitmenttask.product.ProductEntity;

public record ProductResponse(
        ProductEntity productEntity,
        String details
) {
}

