package com.kroll;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Cart cart = new Cart();
        int orderId = 1;
        Random random = new Random();
        List<Order> orders = new ArrayList<>();
        List<Product> availableProducts = Product.createAvailableProducts();

        while (true) {
            System.out.println("Виберіть опцію:");
            System.out.println("1. Додати продукт до кошика");
            System.out.println("2. Видалити продукт з кошика");
            System.out.println("3. Переглянути вміст кошика");
            System.out.println("4. Зробити замовлення");
            System.out.println("5. Перевірити статус замовлення");
            System.out.println("0. Вийти");

            int option = scanner.nextInt();
            switch (option) {
                case 1:
                    displayAvailableProducts(availableProducts);
                    int productId = scanner.nextInt();
                    Product productToAdd = getProductById(availableProducts, productId);
                    if (productToAdd != null) {
                        cart.addProduct(productToAdd);
                        System.out.println("Продукт додано до кошика.");
                    }
                    break;
                case 2:
                    cart.displayCartContent();
                    int productToRemove = scanner.nextInt();
                    Product removedProduct = getProductById(cart.getProducts(), productToRemove);
                    if (removedProduct != null) {
                        cart.removeProduct(removedProduct);
                        System.out.println("Продукт видалено з кошика.");
                    }
                    break;
                case 3:
                    cart.displayCartContent();
                    break;
                case 4:
                    if (!cart.getProducts().isEmpty()) {
                        Order order = new Order(orderId++, new ArrayList<>(cart.getProducts()), 9);
                        orders.add(order);
                        System.out.println("Замовлення створено.");
                        cart.clearCart();
                    } else {
                        System.out.println("Кошик порожній. Спочатку додайте продукти.");
                    }
                    break;
                case 5:
                    checkOrderStatus(orders);
                    break;
                case 0:
                    System.out.println("До побачення!");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Невірний вибір. Спробуйте ще раз.");
            }

            decrementOrderStatus(orders);
        }
    }

    private static void displayAvailableProducts(List<Product> availableProducts) {
        System.out.println("Доступні продукти:");
        for (Product product : availableProducts) {
            System.out.println(product.getId() + ". " + product.getName() + " - " + product.getPrice());
        }
        System.out.println("Виберіть продукт за ID:");
    }

    private static Product getProductById(List<Product> productList, int productId) {
        for (Product product : productList) {
            if (product.getId() == productId) {
                return product;
            }
        }
        System.out.println("Продукт з вказаним ID не знайдено.");
        return null;
    }

    private static void checkOrderStatus(List<Order> orders) {
        if (orders.isEmpty()) {
            System.out.println("Замовлення не було зроблене.");
        } else {
            for (Order order : orders) {
                System.out.println("Номер замовлення: " + order.getOrderId() + ". Вміст: " + order.getProductsList() +
                        ". Статус замовлення: " + (order.getStatus() > 0 ? order.getStatus() + " секунд" : "Готово."));
            }
        }
    }

    private static void decrementOrderStatus(List<Order> orders) {
        for (Order order : orders) {
            order.decrementStatus();
        }
    }
}

