package com.equalexperts.shoppingcart;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collections;
import java.util.Map;
import java.util.HashMap;
import java.util.Objects;

public class ShoppingCart {

    private Map<Product, Integer> products;

    private static final double TAX_PAYABLE_PERCENTAGE = 12.5;

    public ShoppingCart() {
        this.products = new HashMap<>();
    }

    public void addProduct(Product product, int quantity) {
        if (Objects.isNull(product)) {
            throw new IllegalArgumentException("A Product must be provided");
        }
        int currentQuantity = products.getOrDefault(product, 0);
        products.put(product, currentQuantity + quantity);
    }

    public Map<Product, Integer> getProducts() {
        return Collections.unmodifiableMap(products);
    }

    public double subTotal() {
        double subTotal = calculateSubTotal();
        return round(subTotal);
    }

    public double taxPayable() {
        double subTotal = calculateSubTotal();
        double taxPayable = subTotal * TAX_PAYABLE_PERCENTAGE / 100;
        return round(taxPayable);
    }

    public double totalPayable() {
        double subTotal = calculateSubTotal();
        double taxPayable = taxPayable();
        return round(subTotal + taxPayable);
    }

    private double calculateSubTotal() {
        double subTotal = 0.00;

        for (Map.Entry<Product, Integer> product : products.entrySet()) {
            subTotal += product.getValue() * product.getKey().getPrice();
        }

        return subTotal;
    }

    private double round(double value) {
        BigDecimal bigDecimal = BigDecimal.valueOf(value);
        return bigDecimal.setScale(2, RoundingMode.HALF_UP).doubleValue();
    }
}