package com.example.order.processing;

import com.example.order.storage.exception.InvalidOrderException;
import com.example.order.storage.model.Product;

public class OrderProcessor<T extends Product> {
    private final T product;

    public OrderProcessor(T product) throws InvalidOrderException {
        if (product == null) {
            throw new InvalidOrderException("Товар не може бути null!");
        }
        if (product.getPrice() <= 0) {
            throw new InvalidOrderException("Ціна товару повинна бути більшою за 0! Товар: " + product.getName());
        }
        this.product = product;
    }

    public void processOrder() {
        System.out.println(String.format("[Потік %s] Обробка замовлення %s | Ціна: $%.2f | Тип: %s",
                Thread.currentThread().getName(),
                product.getName(),
                product.getPrice(),
                product.getClass().getSimpleName()));
    }

    public static <E extends Product> void processSingle(E product) {
        try {
            OrderProcessor<E> processor = new OrderProcessor<>(product);
            processor.processOrder();
        } catch (InvalidOrderException e) {
            System.err.println("Помилка обробки замовлення: " + e.getMessage());
        }
    }
}