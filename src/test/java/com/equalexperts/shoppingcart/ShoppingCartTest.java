package com.equalexperts.shoppingcart;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ShoppingCartTest {

    @Test
    @Order(1)
    public void Should_Create_New_ShoppingCart_Instance() {
        ShoppingCart shoppingCart = new ShoppingCart();

        assertThat(shoppingCart.getProducts().size(), is(0));
        assertThat(shoppingCart.subTotal(), is(0.0));
        assertThat(shoppingCart.taxPayable(), is(0.0));
        assertThat(shoppingCart.totalPayable(), is(0.0));
    }

    @Test
    @Order(2)
    public void Should_Throw_An_IllegalArgument_Exception_When_Adding_Null_Product_To_The_Shopping_Cart() {
        try {
            ShoppingCart shoppingCart = new ShoppingCart();
            shoppingCart.addProduct(null, 2);
        } catch (IllegalArgumentException e) {
            assertThat(e.getMessage(), is("A Product must be provided"));
        }
    }

    @Test
    @Order(3)
    public void Should_Add_A_Product_To_The_ShoppingCart() {
        Product cornflakes = new Product("cornflakes", 2.52);

        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.addProduct(cornflakes, 1);

        assertThat(shoppingCart.getProducts().size(), is(1));
    }

    @Test
    @Order(4)
    public void Should_Add_Multiple_Products_To_The_ShoppingCart() {
        Product cornflakes = new Product("cornflakes", 2.52);
        Product weetabix = new Product("weetabix", 9.98);

        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.addProduct(cornflakes, 1);
        shoppingCart.addProduct(weetabix, 4);

        assertThat(shoppingCart.getProducts().size(), is(2));
    }

    @Test
    @Order(5)
    public void Should_Add_The_Same_Product_Multiple_Times_To_The_ShoppingCart() {
        Product cornflakes = new Product("cornflakes", 2.52);

        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.addProduct(cornflakes, 1);
        shoppingCart.addProduct(cornflakes, 4);

        assertThat(shoppingCart.getProducts().size(), is(1));
        assertThat(shoppingCart.getProducts().get(cornflakes), is(5));
    }

    @Test
    @Order(6)
    public void Should_Calculate_SubTotal_Given_Products_In_The_ShoppingCart() {
        Product cornflakes = new Product("cornflakes", 2.52);
        Product weetabix = new Product("weetabix",  9.98);

        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.addProduct(cornflakes, 2);
        shoppingCart.addProduct(weetabix, 1);

        assertThat(shoppingCart.subTotal(), is(15.02));
    }

    @Test
    @Order(7)
    public void Should_Calculate_Tax_Payable_Given_Products_In_The_ShoppingCart() {
        Product cornflakes = new Product("cornflakes", 2.52);
        Product weetabix = new Product("weetabix",  9.98);

        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.addProduct(cornflakes, 2);
        shoppingCart.addProduct(weetabix, 1);

        assertThat(shoppingCart.taxPayable(), is(1.88));
    }

    @Test
    @Order(8)
    public void Should_Calculate_Total_Payable_Given_Products_In_The_ShoppingCart() {
        Product cornflakes = new Product("cornflakes", 2.52);
        Product weetabix = new Product("weetabix",  9.98);

        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.addProduct(cornflakes, 2);
        shoppingCart.addProduct(weetabix, 1);

        assertThat(shoppingCart.totalPayable(), is(16.90));
    }
}