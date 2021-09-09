package dev.rneacy.dto;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Item {
    private String name;
    private BigDecimal price;

    public Item(String name, String price) {
        this.name = name;
        setPrice(price);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = new BigDecimal(price);
        this.price = this.price.setScale(2, RoundingMode.HALF_UP);
    }
}
