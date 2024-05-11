package com.example.siirecruitmenttask.product.controller.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record ProductRequest(
        @NotBlank(message = "Name cannot be blank")
        @NotNull(message = "Name cannot be null")
        @NotEmpty(message = "Name cannot be empty")
        String name,

        String description,

        @NotNull(message = "Price cannot be null")
        @Min(value = 0, message = "Price cannot be a negative number")
        BigDecimal price,

        @NotNull(message = "Currency cannot be null")
        @NotEmpty(message = "Currency cannot be empty")
        @NotBlank(message = "Currency cannot be blank")
        String currency
) {
}
