package com.example.siirecruitmenttask.discount;

import com.example.siirecruitmenttask.discount.controller.model.CheckDiscountResponse;
import com.example.siirecruitmenttask.exception.ProductNotFoundException;
import com.example.siirecruitmenttask.exception.PromotionalCodeNotFoundException;
import com.example.siirecruitmenttask.product.ProductEntity;
import com.example.siirecruitmenttask.product.ProductService;
import com.example.siirecruitmenttask.promotionalCode.PromotionalCodeEntity;
import com.example.siirecruitmenttask.promotionalCode.PromotionalCodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class DiscountService {

    private final ProductService productService;
    private final PromotionalCodeService promotionalCodeService;

    public CheckDiscountResponse checkDiscount(Long productId, String promotionalCodeName) throws ProductNotFoundException, PromotionalCodeNotFoundException {

        ProductEntity productEntity = productService.findProductById(productId);
        PromotionalCodeEntity promotionalCodeEntity = promotionalCodeService.findByName(promotionalCodeName);

        if (LocalDate.now().compareTo(promotionalCodeEntity.getExpirationDate()) > 0) {
            return new CheckDiscountResponse(productEntity.getPrice(), "This code has expired.");
        }

        if (!Objects.equals(promotionalCodeEntity.getCurrency(), productEntity.getCurrency())) {
            return new CheckDiscountResponse(productEntity.getPrice(), "Currency types differ.");
        }

        if (promotionalCodeEntity.getRemainingUses() < 1) {
            return new CheckDiscountResponse(productEntity.getPrice(), "This code has reached usage limit.");
        }

        BigDecimal discountedPrice = getDiscountPrice(promotionalCodeEntity, productEntity);
        discountedPrice = discountedPrice.setScale(2, RoundingMode.HALF_UP);

        return new CheckDiscountResponse(discountedPrice, "Discount successfully applied");
    }

    public BigDecimal getDiscountPrice(PromotionalCodeEntity promotionalCodeEntity, ProductEntity product) {

        var productPrice = product.getPrice();
        var promotionalCodeAmount = promotionalCodeEntity.getAmount();
        var subtract = calculateSubtract(promotionalCodeEntity, productPrice, promotionalCodeAmount);

        var discountPrice = productPrice.subtract(subtract);
        if (discountPrice.compareTo(BigDecimal.ZERO) < 0) {
            return BigDecimal.ZERO;
        }

        return discountPrice;
    }

    private BigDecimal calculateSubtract(PromotionalCodeEntity promotionalCodeEntity, BigDecimal productPrice, BigDecimal promotionalCodeAmount) {

        if (promotionalCodeEntity.isPercentage()) {
            var denominator = BigDecimal.valueOf(100);
            return productPrice.multiply(promotionalCodeAmount.divide(denominator));
        }

        return promotionalCodeAmount;
    }
}
