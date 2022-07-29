package com.equalexperts.shoppingcart;

import org.junit.jupiter.api.*;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.text.DecimalFormat;
import java.util.Map;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ShoppingCartIntegrationTest {

    private static Map<String, Product> productPrices;

    @BeforeAll
    public static void setup() {
        HttpConnector connector = new HttpConnector();
        productPrices = connector.getProductPrices();
    }

    @Test
    @Order(1)
    public void Should_Add_One_Product_To_The_ShoppingCart() {
        Product cornflakes = productPrices.get("cornflakes");

        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.addProduct(cornflakes, 2);

        assertThat(shoppingCart.getProducts().containsKey(cornflakes), is(true));
        assertThat(shoppingCart.getProducts().get(cornflakes), is(2));

        assertThat(shoppingCart.subTotal(), is(5.04));
        assertThat(shoppingCart.taxPayable(), is(0.63));
        assertThat(shoppingCart.totalPayable(), is(5.67));

        printCart(shoppingCart);
    }

    @Test
    @Order(2)
    public void Should_Add_Multiple_Products_To_The_ShoppingCart() {
        Product cornflakes = productPrices.get("cornflakes");
        Product weetabix = productPrices.get("weetabix");

        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.addProduct(cornflakes, 2);
        shoppingCart.addProduct(weetabix, 1);

        assertThat(shoppingCart.getProducts().containsKey(cornflakes), is(true));
        assertThat(shoppingCart.getProducts().get(cornflakes), is(2));

        assertThat(shoppingCart.getProducts().containsKey(weetabix), is(true));
        assertThat(shoppingCart.getProducts().get(weetabix), is(1));
        assertThat(shoppingCart.subTotal(), is(15.02));
        assertThat(shoppingCart.taxPayable(), is(1.88));
        assertThat(shoppingCart.totalPayable(), is(16.90));

        printCart(shoppingCart);
    }

    @Test
    @Order(3)
    public void Should_Add_Multiple_Products_Multiple_Times_To_The_ShoppingCart() {
        Product cornflakes = productPrices.get("cornflakes");
        Product weetabix = productPrices.get("weetabix");
        Product cheerios = productPrices.get("cheerios");
        Product shreddies = productPrices.get("shreddies");
        Product frosties = productPrices.get("frosties");

        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.addProduct(cornflakes, 100);
        shoppingCart.addProduct(cornflakes, 66);
        shoppingCart.addProduct(weetabix, 33);
        shoppingCart.addProduct(weetabix, 666);
        shoppingCart.addProduct(cheerios, 333);
        shoppingCart.addProduct(cheerios, 10);
        shoppingCart.addProduct(shreddies, 667);
        shoppingCart.addProduct(shreddies, 42);
        shoppingCart.addProduct(frosties, 543);
        shoppingCart.addProduct(frosties, 987);

        assertThat(shoppingCart.getProducts().containsKey(cornflakes), is(true));
        assertThat(shoppingCart.getProducts().get(cornflakes), is(166));

        assertThat(shoppingCart.getProducts().containsKey(weetabix), is(true));
        assertThat(shoppingCart.getProducts().get(weetabix), is(699));

        assertThat(shoppingCart.getProducts().containsKey(cheerios), is(true));
        assertThat(shoppingCart.getProducts().get(cheerios), is(343));

        assertThat(shoppingCart.getProducts().containsKey(shreddies), is(true));
        assertThat(shoppingCart.getProducts().get(shreddies), is(709));

        assertThat(shoppingCart.getProducts().containsKey(frosties), is(true));
        assertThat(shoppingCart.getProducts().get(frosties), is(1530));

        assertThat(shoppingCart.subTotal(), is(21238.65));
        assertThat(shoppingCart.taxPayable(), is(2654.83));
        assertThat(shoppingCart.totalPayable(), is(23893.48));

        printCart(shoppingCart);
    }

    private void printCart(ShoppingCart shoppingCart) {
        DecimalFormat decimalFormat = new DecimalFormat("###,###0.00");
        shoppingCart.getProducts().forEach((key, value) -> {
            System.out.print(value);
            System.out.print(" x ");
            System.out.print(key.getName());
            System.out.print(" @ ");
            System.out.print(key.getPrice());
            System.out.print(" each");
            System.out.print("\n");
        });

        System.out.println("Subtotal = " + decimalFormat.format(shoppingCart.subTotal()));
        System.out.println("Tax = " + decimalFormat.format(shoppingCart.taxPayable()));
        System.out.println("Total = " + decimalFormat.format(shoppingCart.totalPayable()));
    }
}
