package com.pluralsight.NorthwindTradersAPI.models;

public class Product {
     private Integer productId;
     private String productName;
     private Integer categoryId;
     private double unitPrice;

    public Product(Integer productId, String productName, Integer categoryId, double unitPrice) {
        this.productId = productId;
        this.productName = productName;
        this.categoryId = categoryId;
        this.unitPrice = unitPrice;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    @Override
    public String toString() {
        return String.format("%-10d | %-30s | %-10d | $%-10.2f",
                productId, productName, categoryId, unitPrice);
    }
}
