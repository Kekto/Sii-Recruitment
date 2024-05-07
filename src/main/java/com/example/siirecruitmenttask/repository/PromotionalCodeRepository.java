package com.example.siirecruitmenttask.repository;

import com.example.siirecruitmenttask.model.PromotionalCode;
import org.springframework.data.repository.CrudRepository;

public interface PromotionalCodeRepository extends CrudRepository<PromotionalCode, Integer> {
    PromotionalCode findByCode(String code);
}
