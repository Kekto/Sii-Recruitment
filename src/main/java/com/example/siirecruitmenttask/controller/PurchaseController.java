package com.example.siirecruitmenttask.controller;

import com.example.siirecruitmenttask.record.ProductPromocode;
import com.example.siirecruitmenttask.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class PurchaseController {
    @Autowired
    private PurchaseService purchaseService;

    @RequestMapping(value = "/purchases", method = RequestMethod.POST)
    public ResponseEntity<?> checkDiscount(){
        return purchaseService.getAllPurchases();
    }

    @RequestMapping(value = "/purchase", method = RequestMethod.POST)
    public ResponseEntity<?> checkDiscount(@RequestBody ProductPromocode productPromocode){
        return purchaseService.purchaseProduct(productPromocode.productId(), productPromocode.promotionalCodeName());
    }
}
