package com.example.siirecruitmenttask.discount.controller;

import com.example.siirecruitmenttask.exception.ProductNotFoundException;
import com.example.siirecruitmenttask.exception.PromotionalCodeNotFoundException;
import com.example.siirecruitmenttask.discount.controller.model.CheckDiscountResponse;
import com.example.siirecruitmenttask.purchase.controller.model.PurchaseRequest;
import com.example.siirecruitmenttask.discount.DiscountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = DiscountController.DISCOUNT_ENDPOINT_V1, produces = MediaType.APPLICATION_JSON_VALUE)
public class DiscountController {

    static final String DISCOUNT_ENDPOINT_V1 = "/api/v1/discounts";
    private final DiscountService discountService;

    @GetMapping
    public ResponseEntity<CheckDiscountResponse> checkDiscount(@RequestBody @Valid PurchaseRequest purchaseRequest) {

        try {
            var checkDiscount = discountService.checkDiscount(purchaseRequest.productId(), purchaseRequest.promotionalCodeName());
            return ResponseEntity.status(HttpStatus.OK)
                    .body(checkDiscount);
        } catch (ProductNotFoundException | PromotionalCodeNotFoundException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage(), exception);
        }
    }
}
