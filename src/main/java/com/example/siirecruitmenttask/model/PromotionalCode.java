package com.example.siirecruitmenttask.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "promotional_code")
public class PromotionalCode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    @NotBlank
    @NotNull
    @NotEmpty
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "Attribute must be alphanumeric")
    @Size(min = 3, max = 24, message = "Code length must be between 3 and 24 characters")
    private String code;

    @Column
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull
    private Date expirationDate;

    @Column
    @NotNull
    private BigDecimal amount;

    @Column
    @NotNull
    @NotEmpty
    @NotBlank
    private String currency;

    @Column
    @NotNull
    private int remainingUses;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public int getRemainingUses() {
        return remainingUses;
    }

    public void setRemainingUses(int remainingUses) {
        this.remainingUses = remainingUses;
    }
}
