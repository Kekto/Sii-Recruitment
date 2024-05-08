package com.example.siirecruitmenttask.record;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public record ProductPromocode(
        @NotNull
        @Valid
        Long productId,
        @Valid
        @NotNull
        String promotionalCodeName
) { }
