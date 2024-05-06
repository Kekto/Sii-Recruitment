package com.example.siirecruitmenttask.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Table(name = "promotional_code")
public class PromotionalCode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @NotBlank
    @NotNull
    @NotEmpty
    @Size(min = 3, max = 24, message = "Code length must be between 3 and 24 characters")
    private String code;

    @Column
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull
    @NotEmpty
    @NotBlank
    private Date expirationDate;

    @Column
    @NotNull
    @NotEmpty
    @NotBlank
    private double amount;

    @Column
    @NotNull
    @NotEmpty
    @NotBlank
    private String currency;

    @Column
    @NotNull
    @NotEmpty
    @NotBlank
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

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
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
