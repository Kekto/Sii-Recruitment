package com.example.siirecruitmenttask.record;

import com.example.siirecruitmenttask.model.Product;
import com.example.siirecruitmenttask.model.PromotionalCode;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public record ProductPromocode(
        @NotNull
        @Valid
        Product product,
        @Valid
        @NotNull
        PromotionalCode promotionalCode
) { }
