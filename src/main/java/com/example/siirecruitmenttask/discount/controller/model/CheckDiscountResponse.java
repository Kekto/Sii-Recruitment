package com.example.siirecruitmenttask.discount.controller.model;

import java.math.BigDecimal;

public record CheckDiscountResponse(
        BigDecimal price,
        String currency,
        String details) {
}
