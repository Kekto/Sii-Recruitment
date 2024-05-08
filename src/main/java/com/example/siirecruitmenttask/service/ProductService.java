package com.example.siirecruitmenttask.service;

import com.example.siirecruitmenttask.model.Product;
import com.example.siirecruitmenttask.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public ResponseEntity<?> getAllProducts() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(productRepository.findAll());
    }

    public ResponseEntity<?> addProduct(Product product) {
        if( product.getName().isEmpty()
                || product.getCurrency().isEmpty()
                || product.getPrice().compareTo(BigDecimal.valueOf(0)) < 0 ) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Invalid data");
        }

        productRepository.save(product);
        return ResponseEntity.status(HttpStatus.OK)
                .body("Product has been added");
    }

    public ResponseEntity<?> editProduct(Long id, Product product) {
        if( product.getName().isEmpty()
                || product.getCurrency().isEmpty()
                || product.getPrice().compareTo(BigDecimal.valueOf(0)) < 0 ) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Invalid data");
        }

        Optional<Product> optional = productRepository.findById(Math.toIntExact(id));
        Product productTemp = optional.get();

        productTemp.setName(product.getName());
        productTemp.setPrice(product.getPrice());
        productTemp.setCurrency(product.getCurrency());

        if(!product.getDescription().isEmpty() && !product.getDescription().isBlank()){
            productTemp.setDescription(productTemp.getDescription());
        } else productTemp.setDescription(null);

        productRepository.save(productTemp);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body("Product saved successfully");
    }

    public ResponseEntity<?> deleteProduct(Long id) {
        if(productRepository.existsById(Math.toIntExact(id))){
            productRepository.deleteById(Math.toIntExact(id));
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body("Product has been deleted");
        };
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body("Product with this id does not exist");
    }
}
