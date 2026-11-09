package com.example.order.app;

import com.example.order.concurrency.OrderThreadManager;
import com.example.order.storage.model.Clothing;
import com.example.order.storage.model.Electronics;
import com.example.order.storage.model.Product;
import com.example.order.storage.repository.OrderRepository;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Запуск системи обробки замовлень ===");

        OrderRepository repository = new OrderRepository();
        List<Product> orders = repository.generateSampleOrders(10);

        System.out.println("\n--- Всі згенеровані замовлення ---");
        orders.forEach(System.out::println);

        OrderThreadManager threadManager = new OrderThreadManager(3);

        System.out.println("\n--- Багатопотокова обробка електроніки (> $100) ---");
        threadManager.processOrdersConcurrently(
                orders,
                product -> product instanceof Electronics && product.getPrice() > 100.0
        );

        System.out.println("\n--- Багатопотокова обробка одягу ---");
        threadManager.processOrdersConcurrently(
                orders,
                product -> product instanceof Clothing
        );

        threadManager.shutdown();
        System.out.println("\n=== Обробку завершено успішно ===");
    }
}