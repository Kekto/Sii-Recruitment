package com.example.siirecruitmenttask.promotionalCode;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(name = "promotional_code")
public class PromotionalCodeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    @Column
    private LocalDate expirationDate;

    @Column
    private BigDecimal amount;

    @Column
    private boolean percentage;

    @Column
    private String currency;

    @Column
    private int remainingUses;
}
