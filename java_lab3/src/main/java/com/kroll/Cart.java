package com.kroll;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<Product> products;

    public Cart() {
        this.products = new ArrayList<>();
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public void removeProduct(Product product) {
        products.remove(product);
    }

    public List<Product> getProducts() {
        return products;
    }

    public void displayCartContent() {
        if (products.isEmpty()) {
            System.out.println("Кошик порожній.");
        } else {
            System.out.println("Вміст кошика:");
            for (int i = 0; i < products.size(); i++) {
                System.out.println((i + 1) + ". " + products.get(i).toString());
            }
        }
    }

    public void clearCart() {
        products.clear();
    }
}
