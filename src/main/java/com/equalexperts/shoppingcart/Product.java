package com.equalexperts.shoppingcart;

import java.util.Objects;

public class Product {

    private final String name;

    private final double price;

    public Product(String name, double price) {
        if (Objects.isNull(name) || name.length() == 0) {
            throw new IllegalArgumentException("A Product name must be provided");
        }
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Product product = (Product) o;
        return name.equalsIgnoreCase(product.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}