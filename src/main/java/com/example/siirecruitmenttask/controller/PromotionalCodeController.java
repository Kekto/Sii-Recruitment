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

    @RequestMapping(value = "/promocode", method = RequestMethod.GET)
    public ResponseEntity<?> getAllTasks(){
        return promotionalCodeService.getAllPromotionalCodes();
    }

    @RequestMapping(value = "/promocode", method = RequestMethod.POST)
    public ResponseEntity<?> addTask(@RequestBody PromotionalCode promotionalCode){
        return promotionalCodeService.addPromotionalCode(promotionalCode);
    }

    @RequestMapping(value = "/promocode/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> editTask(@PathVariable Long id, @RequestBody PromotionalCode promotionalCode){
        return promotionalCodeService.editPromotionalCode(id, promotionalCode);
    }

    @RequestMapping(value = "/promocode/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteTask(@PathVariable Long id){
        return promotionalCodeService.deletePromotionalCode(id);
    }
}
