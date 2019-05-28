package com.hobo.merchant.model;

public class NameAndStockDTO {
    private String name;
    private int stock;

    @Override
    public String toString() {
        return "NameAndStockDTO{" +
                "name='" + name + '\'' +
                ", stock=" + stock +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
