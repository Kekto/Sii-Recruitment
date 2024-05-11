package com.example.siirecruitmenttask.purchase;

import com.example.siirecruitmenttask.discount.DiscountService;
import com.example.siirecruitmenttask.exception.ProductNotFoundException;
import com.example.siirecruitmenttask.exception.PromotionalCodeNotFoundException;
import com.example.siirecruitmenttask.product.ProductEntity;
import com.example.siirecruitmenttask.product.ProductService;
import com.example.siirecruitmenttask.promotionalCode.PromotionalCodeEntity;
import com.example.siirecruitmenttask.promotionalCode.PromotionalCodeService;
import com.example.siirecruitmenttask.purchase.controller.model.PurchaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class PurchaseService {
    private final PurchaseRepository purchaseRepository;

    private final ProductService productService;

    private final PromotionalCodeService promotionalCodeService;

    private final DiscountService discountService;

    public List<PurchaseEntity> getAllPurchases() {
        return purchaseRepository.findAll();
    }

    public PurchaseResponse purchaseProduct(Long productId, String promotionalCodeName) throws ProductNotFoundException, PromotionalCodeNotFoundException {

        ProductEntity product = productService.findProductById(productId);

        PurchaseEntity purchaseEntity = new PurchaseEntity();
        purchaseEntity.setPurchaseDate(LocalDate.now());
        purchaseEntity.setBasePrice(product.getPrice());
        purchaseEntity.setCurrency(product.getCurrency());
        purchaseEntity.setProductName(product.getName());

        if (promotionalCodeName != null) {

            PromotionalCodeEntity promotionalCodeEntity = promotionalCodeService.findByName(promotionalCodeName);

            if (LocalDate.now().compareTo(promotionalCodeEntity.getExpirationDate()) > 0) {

                purchaseRepository.save(purchaseEntity);

                return new PurchaseResponse(product.getPrice(), product.getCurrency(), "This code has expired.");
            }

            if (!Objects.equals(promotionalCodeEntity.getCurrency(), product.getCurrency())) {

                purchaseRepository.save(purchaseEntity);

                return new PurchaseResponse(product.getPrice(), product.getCurrency(), "Currency types differ.");
            }

            if (promotionalCodeEntity.getRemainingUses() < 1) {

                purchaseRepository.save(purchaseEntity);

                return new PurchaseResponse(product.getPrice(), product.getCurrency(), "This code has reached usage limit.");
            }

            BigDecimal discountedPrice = discountService.getDiscountPrice(promotionalCodeEntity, product);
            discountedPrice = discountedPrice.setScale(2, RoundingMode.HALF_UP);

            purchaseEntity.setDiscountValue(promotionalCodeEntity.getAmount());
            purchaseEntity.setPercentage(promotionalCodeEntity.isPercentage());
            promotionalCodeEntity.setRemainingUses(promotionalCodeEntity.getRemainingUses() - 1);

            purchaseRepository.save(purchaseEntity);
            promotionalCodeService.save(promotionalCodeEntity);

            return new PurchaseResponse(discountedPrice, product.getCurrency(), "Discount applied successfully.");
        }

        purchaseRepository.save(purchaseEntity);

        return new PurchaseResponse(product.getPrice(), product.getCurrency(), "Product purchased successfully.");
    }
}
