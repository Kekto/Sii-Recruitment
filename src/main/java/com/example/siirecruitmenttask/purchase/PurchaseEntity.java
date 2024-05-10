package com.example.siirecruitmenttask.purchase;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(name = "purchase")
public class PurchaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @NotNull
    @NotEmpty
    @NotBlank
    private String productName;

    @Column
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull
    private LocalDate purchaseDate;

    @Column
    @NotNull
    private BigDecimal basePrice;

    @Column
    @NotNull
    @NotBlank
    @NotEmpty
    private String currency;

    @Column
    private BigDecimal discountValue;

    @Column
    @NotNull
    private boolean percantage;
}
