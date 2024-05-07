package com.example.siirecruitmenttask.repository;

import com.example.siirecruitmenttask.model.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Integer> {
}
