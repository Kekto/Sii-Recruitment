package com.example.siirecruitmenttask.promotionalCode.controller.model;

import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;

public record PromotionalCodeRequest(
        @NotBlank
        @NotNull
        @NotEmpty
        @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "Attribute must be alphanumeric")
        @Size(min = 3, max = 24, message = "Code length must be between 3 and 24 characters")
        String name,

        @DateTimeFormat(pattern = "yyyy-MM-dd")
        @NotNull
        LocalDate expirationDate,

        @NotNull
        BigDecimal amount,

        @NotNull
        boolean percantage,

        @NotNull
        @NotEmpty
        @NotBlank
        String currency,

        @NotNull
        int remainingUses
) {
}
