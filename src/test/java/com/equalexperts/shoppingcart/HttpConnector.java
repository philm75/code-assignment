package com.equalexperts.shoppingcart;

import java.math.BigDecimal;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.*;

import org.json.JSONObject;

public class HttpConnector {

    private static final String TEST_DATA_URL = "https://equalexperts.github.io/backend-take-home-test-data/%s.json";

    private static final List<String> PRODUCT_NAMES = Arrays.asList(
            "cheerios",
            "cornflakes",
            "frosties",
            "shreddies",
            "weetabix");

    private HttpClient client;

    public HttpConnector() {
        this.client = HttpClient.newHttpClient();
    }

    private String constructUrl(String productName) {
        return String.format(TEST_DATA_URL, productName);
    }

    public Map<String, Product> getProductPrices() {
        Map<String, Product> products = new HashMap<>();

       for (String productName: PRODUCT_NAMES) {
           String url = this.constructUrl(productName);

           HttpRequest request = HttpRequest.newBuilder()
                   .uri(URI.create(url))
                   .header("Accept", "application/json")
                   .build();

           try {
               HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
               JSONObject jsonObject = new JSONObject(response.body());
               String title = (String)jsonObject.get("title");
               BigDecimal price = (BigDecimal)jsonObject.get("price");

               products.put(productName, new Product(title, price.doubleValue()));
           } catch (Exception e) {
               System.out.println("Product prices are not available for " + productName);
           }
       }

       return products;
    }
}
