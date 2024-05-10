package com.example.siirecruitmenttask.purchase.controller.model;

import java.math.BigDecimal;

public record PurchaseResponse(
        BigDecimal priceAfterDiscount,
        String currency,
        String details) {
}
