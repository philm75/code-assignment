package com.equalexperts.shoppingcart;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ProductTest {

    @Test
    @Order(1)
    public void Constructor_Should_Create_New_Product_Given_Name_And_Price() {
        Product actualProduct = new Product("cornflakes",2.52);

        assertThat(actualProduct.getName(), is("cornflakes"));
        assertThat(actualProduct.getPrice(), is(2.52));
    }

    @Test
    @Order(2)
    public void Constructor_Should_Throw_An_IllegalArgument_Exception_When_Setting_Null_Product_Name() {
        try {
            new Product(null, 2.00);
        } catch (IllegalArgumentException e) {
            assertThat(e.getMessage(), is("A Product name must be provided"));
        }
    }

    @Test
    @Order(3)
    public void Constructor_Should_Throw_An_IllegalArgument_Exception_When_Setting_Blank_Product_Name() {
        try {
            new Product("", 2.00);
        } catch (IllegalArgumentException e) {
            assertThat(e.getMessage(), is("A Product name must be provided"));
        }
    }

    @Test
    @Order(4)
    public void Equals_Should_Return_True_When_Products_Are_Equal() {
        Product product = new Product("cornflakes",2.52);
        Product otherProduct = new Product("Cornflakes",2.52);

        boolean actualResult = product.equals(otherProduct);

        assertThat(actualResult, is(true));
    }

    @Test
    @Order(5)
    public void Equals_Should_Return_True_When_Product_Names_Are_Equal() {
        Product product = new Product("cornflakes",2.52);
        Product otherProduct = new Product("cornFLakes",5.52);

        boolean actualResult = product.equals(otherProduct);

        assertThat(actualResult, is(true));
    }

    @Test
    @Order(6)
    public void Equals_Should_Return_False_When_Products_Are_Different() {
        Product product = new Product("cornflakes",2.52);
        Product otherProduct = new Product("weetabix",5.52);

        boolean actualResult = product.equals(otherProduct);

        assertThat(actualResult, is(false));
    }

    @Test
    @Order(7)
    public void Equals_Should_Return_False_When_The_Other_Product_Is_Null() {
        Product product = new Product("cornflakes",2.52);
        Product otherProduct = null;

        boolean actualResult = product.equals(otherProduct);

        assertThat(actualResult, is(false));
    }

    @Test
    @Order(8)
    public void Equals_Should_Return_False_When_The_Other_Product_Is_Not_A_Product() {
        Product product = new Product("cornflakes",2.52);
        String productName = "cornflakes";

        boolean actualResult = product.equals(productName);

        assertThat(actualResult, is(false));
    }

    @Test
    @Order(9)
    public void Hash_Code_Should_Return_An_Integer_For_A_Product_Name() {
        Product product = new Product("cornflakes",2.52);
        int actualHashCode = product.hashCode();

        assertThat(actualHashCode, is(1936998854));
    }
}