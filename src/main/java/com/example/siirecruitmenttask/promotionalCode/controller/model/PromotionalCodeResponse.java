package com.example.siirecruitmenttask.promotionalCode.controller.model;

import com.example.siirecruitmenttask.promotionalCode.PromotionalCodeEntity;

public record PromotionalCodeResponse(
        PromotionalCodeEntity promotionalCodeEntity,
        String details ) {
}
