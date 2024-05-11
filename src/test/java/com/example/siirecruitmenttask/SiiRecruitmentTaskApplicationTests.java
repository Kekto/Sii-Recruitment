package com.example.siirecruitmenttask;

import com.example.siirecruitmenttask.discount.DiscountService;
import com.example.siirecruitmenttask.product.ProductEntity;
import com.example.siirecruitmenttask.promotionalCode.PromotionalCodeEntity;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.hamcrest.MatcherAssert.assertThat;

@SpringBootTest
class SiiRecruitmentTaskApplicationTests {

    @Autowired
    DiscountService discountService;

    @Test
    void getDiscountPrice_whenCorrectCodeProvided_ReturnOk() {
        //given
        ProductEntity product = createBasicProduct();

        PromotionalCodeEntity promocode = createBasicPromotionalCode();

        BigDecimal expectedDiscountValue = BigDecimal.valueOf(30);

        // when
        var actualDiscountedValue = discountService.getDiscountPrice(promocode, product);

        // then
        assertThat(actualDiscountedValue, Matchers.comparesEqualTo(expectedDiscountValue));
    }

    @Test
    void getDiscountPrice_whenCorrectCodeProvidedWithPercentage_ReturnOk() {
        //given
        ProductEntity product = createBasicProduct();

        PromotionalCodeEntity promocode = createBasicPromotionalCode();
        promocode.setPercentage(true);

        BigDecimal expectedDiscountValue = BigDecimal.valueOf(40);

        // when
        var actualDiscountedValue = discountService.getDiscountPrice(promocode, product);

        // then
        assertThat(actualDiscountedValue, Matchers.comparesEqualTo(expectedDiscountValue));
    }

    ProductEntity createBasicProduct() {

        ProductEntity product = new ProductEntity();
        product.setId(1L);
        product.setName("PRODUCT01");
        product.setPrice(BigDecimal.valueOf(50));
        product.setCurrency("PLN");

        return product;
    }

    PromotionalCodeEntity createBasicPromotionalCode() {

        PromotionalCodeEntity promocode = new PromotionalCodeEntity();
        promocode.setId(1L);
        promocode.setName("PROMOCODE01");
        promocode.setExpirationDate(LocalDate.ofEpochDay(2024-05-30));
        promocode.setAmount(BigDecimal.valueOf(20));
        promocode.setCurrency("PLN");

        return promocode;
    }

}
