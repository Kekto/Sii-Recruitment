package com.example.siirecruitmenttask.controller;

import com.example.siirecruitmenttask.model.Product;
import com.example.siirecruitmenttask.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class ProductController {
    @Autowired
    private ProductService productService;

    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public ResponseEntity<?> getAllProducts(){
        return productService.getAllProducts();
    }

    @RequestMapping(value = "/product", method = RequestMethod.POST)
    public ResponseEntity<?> addProduct(@RequestBody Product product){
        return productService.addProduct(product);
    }

    @RequestMapping(value = "/product/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> editProduct(@PathVariable Long id, @RequestBody Product product){
        return productService.editProduct(id, product);
    }

    @RequestMapping(value = "/product/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteProduct(@PathVariable Long id){
        return productService.deleteProduct(id);
    }

}
