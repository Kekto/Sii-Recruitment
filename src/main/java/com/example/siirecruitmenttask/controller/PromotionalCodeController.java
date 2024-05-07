package com.example.siirecruitmenttask.controller;

import com.example.siirecruitmenttask.model.PromotionalCode;
import com.example.siirecruitmenttask.service.PromotionalCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class PromotionalCodeController {
    @Autowired
    private PromotionalCodeService promotionalCodeService;

    @RequestMapping(value = "/promocodes", method = RequestMethod.GET)
    public ResponseEntity<?> getAllPromotionalCodes(){
        return promotionalCodeService.getAllPromotionalCodes();
    }

    @RequestMapping(value = "/promocode/{code}", method = RequestMethod.GET)
    public ResponseEntity<?> getPromotionalCodeByCode(@PathVariable String code){
        return promotionalCodeService.getPromotionalCodeByCode(code);
    }

    @RequestMapping(value = "/promocode", method = RequestMethod.POST)
    public ResponseEntity<?> addPromotionalCode(@RequestBody PromotionalCode promotionalCode){
        return promotionalCodeService.addPromotionalCode(promotionalCode);
    }

    @RequestMapping(value = "/promocode/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> editPromotionalCode(@PathVariable Long id, @RequestBody PromotionalCode promotionalCode){
        return promotionalCodeService.editPromotionalCode(id, promotionalCode);
    }

    @RequestMapping(value = "/promocode/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deletePromotionalCode(@PathVariable Long id){
        return promotionalCodeService.deletePromotionalCode(id);
    }
}
