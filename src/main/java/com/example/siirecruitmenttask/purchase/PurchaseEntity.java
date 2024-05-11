package com.example.siirecruitmenttask.purchase;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    @NotNull(message = "Product name cannot be null")
    @NotEmpty(message = "Product name cannot be empty")
    @NotBlank(message = "Product name cannot be blank")
    private String productName;

    @Column
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "Purchase date cannot be null")
    private LocalDate purchaseDate;

    @Column
    @NotNull(message = "Base price cannot be null")
    private BigDecimal basePrice;

    @Column
    @NotNull(message = "Currency cannot be null")
    @NotBlank(message = "Currency cannot be blank")
    @NotEmpty(message = "Currency cannot be empty")
    private String currency;

    @Column
    private BigDecimal discountValue;

    @Column
    @NotNull(message = "Percentage cannot be null")
    private boolean percentage;
}
