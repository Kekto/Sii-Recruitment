package com.example.siirecruitmenttask.purchase.controller.model;

import jakarta.validation.constraints.NotNull;

public record PurchaseRequest(
        @NotNull
        Long productId,
        String promotionalCodeName
) {
}
