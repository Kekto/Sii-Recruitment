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
    @NotBlank
    @NotNull
    @NotEmpty
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "Attribute must be alphanumeric")
    @Size(min = 3, max = 24, message = "Code length must be between 3 and 24 characters")
    private String name;

    @Column
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull
    private LocalDate expirationDate;

    @Column
    @NotNull
    private BigDecimal amount;

    @Column
    @NotNull
    private boolean percantage;

    @Column
    @NotNull
    @NotEmpty
    @NotBlank
    private String currency;

    @Column
    @NotNull
    private int remainingUses;
}
