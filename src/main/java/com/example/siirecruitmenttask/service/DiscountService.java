package com.example.siirecruitmenttask.service;

import com.example.siirecruitmenttask.model.Product;
import com.example.siirecruitmenttask.model.PromotionalCode;
import com.example.siirecruitmenttask.repository.ProductRepository;
import com.example.siirecruitmenttask.repository.PromotionalCodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

@Service
public class DiscountService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private PromotionalCodeRepository promotionalCodeRepository;

    public ResponseEntity<?> checkDiscount(Product product, PromotionalCode promotionalCode) {

        if(!productRepository.existsById(Math.toIntExact(product.getId()))){
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("This product does not exist");
        }

        if(promotionalCodeRepository.findByCode(promotionalCode.getCode()) == null ){
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Promotional Code not found");
        }

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

        double discountedPrice = product.getPrice() - promotionalCode.getAmount() <= 0 ? 0 : product.getPrice() - promotionalCode.getAmount();

        return ResponseEntity.status(HttpStatus.OK)
                .body("Discounted price for " + product.getName() + " using the code " + promotionalCode.getCode() +  " is: " + discountedPrice);
    }
}
