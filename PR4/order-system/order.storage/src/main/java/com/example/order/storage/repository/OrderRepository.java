package com.example.order.storage.repository;

import com.example.order.storage.model.Clothing;
import com.example.order.storage.model.Electronics;
import com.example.order.storage.model.Product;
import com.github.javafaker.Faker;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class OrderRepository {
    private final Faker faker = new Faker();

    public List<Product> generateSampleOrders(int count) {
        List<Product> products = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            if (i % 2 == 0) {
                products.add(Electronics.builder()
                        .id(UUID.randomUUID().toString())
                        .name(faker.commerce().productName())
                        .price(Double.parseDouble(faker.commerce().price(50.0, 1500.0).replace(',', '.')))
                        .brand(faker.company().name())
                        .warrantyMonths(faker.number().numberBetween(12, 36))
                        .build());
            } else {
                products.add(Clothing.builder()
                        .id(UUID.randomUUID().toString())
                        .name(faker.commerce().productName())
                        .price(Double.parseDouble(faker.commerce().price(10.0, 200.0).replace(',', '.')))
                        .size(faker.options().option("S", "M", "L", "XL"))
                        .material(faker.commerce().color())
                        .build());
            }
        }
        return products;
    }
}