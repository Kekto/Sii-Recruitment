package com.example.siirecruitmenttask.service;

import com.example.siirecruitmenttask.model.Product;
import com.example.siirecruitmenttask.model.PromotionalCode;
import com.example.siirecruitmenttask.repository.ProductRepository;
import com.example.siirecruitmenttask.repository.PromotionalCodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;
import java.util.Optional;

@Service
public class DiscountService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private PromotionalCodeRepository promotionalCodeRepository;

    public ResponseEntity<?> checkDiscount(Long productId, String promotionalCodeName) {

        if(!productRepository.existsById(Math.toIntExact(productId))){
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("This product does not exist");
        }

        Optional<Product> optionalProduct = productRepository.findById(Math.toIntExact(productId));
        Product product = optionalProduct.get();

        if(promotionalCodeRepository.findByCode(promotionalCodeName) == null ){
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Promotional Code not found");
        }

        Optional<PromotionalCode> optionalPromocode = Optional.ofNullable(promotionalCodeRepository.findByCode(promotionalCodeName));
        PromotionalCode promotionalCode = optionalPromocode.get();

        if(promotionalCode.getExpirationDate().before(new Date())){
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body("This code has expired. Regular price for this product:" + product.getPrice() + " " + product.getCurrency());
        }

        if(!Objects.equals(promotionalCode.getCurrency(), product.getCurrency())){
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body("Currency types differ. Regular price for this product:" + product.getPrice() + " " + product.getCurrency());
        }

        if(promotionalCode.getRemainingUses() < 1){
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body("This code has reached usage limit. Regular price for this product:" + product.getPrice() + " " + product.getCurrency());
        }

        BigDecimal discountedPrice = product.getPrice().subtract(promotionalCode.getAmount()).compareTo(BigDecimal.valueOf(0)) <= 0 ? BigDecimal.valueOf(0) : product.getPrice().subtract(promotionalCode.getAmount());

        return ResponseEntity.status(HttpStatus.OK)
                .body("Discounted price for " + product.getName() + " using the code " + promotionalCode.getCode() +  " is: " + discountedPrice);
    }
}
