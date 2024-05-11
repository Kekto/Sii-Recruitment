package com.example.siirecruitmenttask.promotionalCode.controller.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;

public record PromotionalCodeRequest(
        @NotBlank(message = "Name cannot be blank")
        @NotNull(message = "Name cannot be null")
        @NotEmpty(message = "Name cannot be empty")
        @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "Name must be alphanumeric")
        @Size(min = 3, max = 24, message = "Name length must be between 3 and 24 characters")
        String name,

        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
        @JsonFormat(pattern = "yyyy-MM-dd")
        @NotNull(message = "Expiration date cannot be null")
        LocalDate expirationDate,

        @NotNull(message = "Amount cannot be null")
        @Min(value = 0, message = "Amount cannot be a negative number")
        BigDecimal amount,

        @NotNull(message = "Percentage cannot be null")
        boolean percentage,

        @NotNull(message = "Currency cannot be null")
        @NotEmpty(message = "Currency cannot be empty")
        @NotBlank(message = "Currency cannot be blank")
        String currency,

        @NotNull(message = "Remaining uses cannot be null")
        @Min(value = 0, message = "Remaining uses cannot be a negative number")
        int remainingUses
) {
}
