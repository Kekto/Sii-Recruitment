package com.example.siirecruitmenttask.controller;

import com.example.siirecruitmenttask.model.Product;
import com.example.siirecruitmenttask.model.PromotionalCode;
import com.example.siirecruitmenttask.record.ProductPromocode;
import com.example.siirecruitmenttask.service.DiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class DiscountController {

    @Autowired
    private DiscountService discountService;

    @RequestMapping(value = "/discount", method = RequestMethod.POST)
    public ResponseEntity<?> checkDiscount(@RequestBody ProductPromocode productPromocode){
        return discountService.checkDiscount(productPromocode.product(), productPromocode.promotionalCode());
    }
}
