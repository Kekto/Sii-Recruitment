package com.example.siirecruitmenttask.product.controller;

import com.example.siirecruitmenttask.exception.ProductInvalidDataException;
import com.example.siirecruitmenttask.exception.ProductNotFoundException;
import com.example.siirecruitmenttask.product.ProductService;
import com.example.siirecruitmenttask.product.ProductEntity;
import com.example.siirecruitmenttask.product.controller.model.ProductRequest;
import com.example.siirecruitmenttask.product.controller.model.ProductResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = ProductController.PRODUCT_ENDPOINT_V1, produces = MediaType.APPLICATION_JSON_VALUE)
public class ProductController {

    static final String PRODUCT_ENDPOINT_V1 = "/api/v1/products";
    private final ProductService productService;

    @GetMapping
    public ResponseEntity<Iterable<ProductEntity>> getAllProducts() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(productService.getAllProducts());
    }

    @PostMapping
    public ResponseEntity<ProductResponse> addProduct(@RequestBody @Valid ProductRequest productRequest) {

        try {
            var addProduct = productService.addProduct(productRequest);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(addProduct);
        } catch (ProductInvalidDataException exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, exception.getMessage(), exception);
        }
    }

    @PutMapping(value = "{id}")
    public ResponseEntity<ProductResponse> editProduct(@PathVariable Long id, @RequestBody @Valid ProductRequest productRequest) {

        try {
            var editProduct = productService.editProduct(id, productRequest);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(editProduct);
        } catch (ProductInvalidDataException exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, exception.getMessage(), exception);
        } catch (ProductNotFoundException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage(), exception);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ProductResponse> deleteProduct(@PathVariable Long id) {

        try {
            var deleteProduct = productService.deleteProduct(id);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(deleteProduct);
        } catch (ProductNotFoundException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage(), exception);
        }
    }

}
