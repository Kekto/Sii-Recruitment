package com.example.siirecruitmenttask.purchase.controller;

import com.example.siirecruitmenttask.exception.ProductNotFoundException;
import com.example.siirecruitmenttask.exception.PromotionalCodeNotFoundException;
import com.example.siirecruitmenttask.purchase.PurchaseEntity;
import com.example.siirecruitmenttask.purchase.PurchaseService;
import com.example.siirecruitmenttask.purchase.controller.model.PurchaseRequest;
import com.example.siirecruitmenttask.purchase.controller.model.PurchaseResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = PurchaseController.PURCHASE_ENDPOINT_V1, produces = MediaType.APPLICATION_JSON_VALUE)
public class PurchaseController {

    static final String PURCHASE_ENDPOINT_V1 = "/api/v1/purchases";
    private final PurchaseService purchaseService;

    @GetMapping
    public ResponseEntity<List<PurchaseEntity>> getAllPurchases() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(purchaseService.getAllPurchases());
    }

    @PostMapping
    public ResponseEntity<PurchaseResponse> purchaseProduct(@RequestBody @Valid PurchaseRequest purchaseRequest) {

        try {
            var purchaseProduct = purchaseService.purchaseProduct(purchaseRequest.productId(), purchaseRequest.promotionalCodeName());
            return ResponseEntity.status(HttpStatus.OK)
                    .body(purchaseProduct);
        } catch (ProductNotFoundException | PromotionalCodeNotFoundException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage(), exception);
        }
    }
}

