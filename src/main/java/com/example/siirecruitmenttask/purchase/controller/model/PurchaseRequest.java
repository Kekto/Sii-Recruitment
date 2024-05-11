package com.example.siirecruitmenttask.purchase.controller.model;

import jakarta.validation.constraints.NotNull;

public record PurchaseRequest(
        @NotNull(message = "Product id cannot be null")
        Long productId,
        String promotionalCodeName
) {
}
