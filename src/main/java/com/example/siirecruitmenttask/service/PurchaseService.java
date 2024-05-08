package com.example.siirecruitmenttask.service;

import com.example.siirecruitmenttask.model.Product;
import com.example.siirecruitmenttask.model.PromotionalCode;
import com.example.siirecruitmenttask.model.Purchase;
import com.example.siirecruitmenttask.repository.ProductRepository;
import com.example.siirecruitmenttask.repository.PromotionalCodeRepository;
import com.example.siirecruitmenttask.repository.PurchaseRepository;
import jakarta.annotation.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;
import java.util.Optional;

@Service
public class PurchaseService {

    @Autowired
    private PurchaseRepository purchaseRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private PromotionalCodeRepository promotionalCodeRepository;

    public ResponseEntity<?> getAllPurchases(){
        return ResponseEntity.status(HttpStatus.OK)
                .body(purchaseRepository.findAll());
    }

    public ResponseEntity<?> purchaseProduct(Long productId, @Nullable String promotionalCodeName) {

        if (!productRepository.existsById(Math.toIntExact(productId))) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("This product does not exist");
        }

        Optional<Product> optionalProduct = productRepository.findById(Math.toIntExact(productId));
        Product product = optionalProduct.get();

        Purchase purchase = new Purchase();
        purchase.setPurchaseDate(new Date());
        purchase.setBasePrice(product.getPrice());
        purchase.setCurrency(product.getCurrency());
        purchase.setProductName(product.getName());

        Optional<PromotionalCode> optionalPromocode = Optional.ofNullable(promotionalCodeRepository.findByCode(promotionalCodeName));

        if (optionalPromocode.isPresent()) {
            PromotionalCode promotionalCode = optionalPromocode.get();

            if (promotionalCode.getExpirationDate().before(new Date())) {

                purchaseRepository.save(purchase);

                return ResponseEntity
                        .status(HttpStatus.OK)
                        .body("This code has expired. Product purchased at regular price of" + product.getPrice() + " " + product.getCurrency());
            }

            if (!Objects.equals(promotionalCode.getCurrency(), product.getCurrency())) {

                purchaseRepository.save(purchase);

                return ResponseEntity
                        .status(HttpStatus.OK)
                        .body("Currency types differ. Product purchased at regular price of" + product.getPrice() + " " + product.getCurrency());
            }

            if (promotionalCode.getRemainingUses() < 1) {

                purchaseRepository.save(purchase);

                return ResponseEntity
                        .status(HttpStatus.OK)
                        .body("This code has reached usage limit. Product purchased at regular price of:" + product.getPrice() + " " + product.getCurrency());
            }

            BigDecimal discountedPrice = product.getPrice().subtract(promotionalCode.getAmount()).compareTo(BigDecimal.valueOf(0)) <= 0 ? BigDecimal.valueOf(0) : product.getPrice().subtract(promotionalCode.getAmount());

            purchase.setDiscountValue(promotionalCode.getAmount());
            promotionalCode.setRemainingUses(promotionalCode.getRemainingUses() - 1);

            purchaseRepository.save(purchase);

            return ResponseEntity.status(HttpStatus.OK)
                    .body("Purchased " + product.getName() + " at discounted price using the code " + promotionalCode.getCode() + " for: " + discountedPrice);
        }

        purchaseRepository.save(purchase);

        return ResponseEntity.status(HttpStatus.OK)
                .body("Purchased " + product.getName() + " for " + product.getPrice() + " " + product.getCurrency());
    }
}
