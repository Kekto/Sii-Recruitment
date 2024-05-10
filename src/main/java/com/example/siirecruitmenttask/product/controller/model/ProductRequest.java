package com.example.siirecruitmenttask.product.controller.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record ProductRequest(
        @NotBlank
        @NotNull
        @NotEmpty
        String name,

        String description,

        @NotNull
        BigDecimal price,

        @NotNull
        @NotEmpty
        @NotBlank
        String currency
) {
}
