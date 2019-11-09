package ru.geekbrains.controller.repr;

import java.math.BigDecimal;

public class ProductFilter {

    private Long categoryId;

    private BigDecimal priceFrom;

    private BigDecimal priceTo;

    public ProductFilter() {
    }

    public ProductFilter(Long categoryId, BigDecimal priceFrom, BigDecimal priceTo) {
        this.categoryId = categoryId;
        this.priceFrom = priceFrom;
        this.priceTo = priceTo;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public BigDecimal getPriceFrom() {
        return priceFrom;
    }

    public void setPriceFrom(BigDecimal priceFrom) {
        this.priceFrom = priceFrom;
    }

    public BigDecimal getPriceTo() {
        return priceTo;
    }

    public void setPriceTo(BigDecimal priceTo) {
        this.priceTo = priceTo;
    }
}
