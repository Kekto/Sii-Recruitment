package com.example.siirecruitmenttask.promotionalCode;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
interface PromotionalCodeRepository extends JpaRepository<PromotionalCodeEntity, Long> {
    Optional<PromotionalCodeEntity> findByName(String name);
}
