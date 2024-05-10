package com.example.siirecruitmenttask.product;

import com.example.siirecruitmenttask.exception.ProductInvalidDataException;
import com.example.siirecruitmenttask.exception.ProductNotFoundException;
import com.example.siirecruitmenttask.product.controller.model.ProductRequest;
import com.example.siirecruitmenttask.product.controller.model.ProductResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public List<ProductEntity> getAllProducts() {
        return productRepository.findAll();
    }

    public ProductResponse addProduct(ProductRequest productRequest) throws ProductInvalidDataException {
        if (productRequest.price().compareTo(BigDecimal.valueOf(0)) < 0) {
            throw new ProductInvalidDataException();
        }

        ProductEntity productEntity = new ProductEntity();
        productEntity.setName(productRequest.name());
        productEntity.setDescription(productRequest.description());
        productEntity.setPrice(productRequest.price());
        productEntity.setCurrency(productRequest.currency());

        productRepository.save(productEntity);
        return new ProductResponse(productEntity, "Product created successfully");
    }

    public ProductResponse editProduct(Long id, ProductRequest productRequest) throws ProductInvalidDataException, ProductNotFoundException {
        if (productRequest.price().compareTo(BigDecimal.valueOf(0)) < 0) {
            throw new ProductInvalidDataException();
        }

        ProductEntity productEntity = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));

        productEntity.setName(productRequest.name());
        productEntity.setPrice(productRequest.price());
        productEntity.setCurrency(productRequest.currency());
        productEntity.setDescription(productRequest.description());

        productRepository.save(productEntity);

        return new ProductResponse(productEntity, "Product saved successfully");
    }

    public ProductResponse deleteProduct(Long id) throws ProductNotFoundException {
        ProductEntity productEntity = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
        productRepository.deleteById(id);

        return new ProductResponse(productEntity, "Product deleted successfully");
    }

    public ProductEntity findProductById(Long productId) throws ProductNotFoundException {

        return productRepository.findById(productId).orElseThrow(() -> new ProductNotFoundException(productId));
    }
}
