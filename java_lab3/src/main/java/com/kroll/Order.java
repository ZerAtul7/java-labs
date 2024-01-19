package com.kroll;

import java.util.List;

public class Order {
    private int orderId;
    private List<Product> products;
    private int status;

    public Order(int orderId, List<Product> products, int status) {
        this.orderId = orderId;
        this.products = products;
        this.status = status;
    }

    public int getOrderId() {
        return orderId;
    }

    public List<Product> getProductsList() {
        return products;
    }

    public int getStatus() {
        return status;
    }

    public void decrementStatus() {
        if (status > 0) {
            status--;
        }
    }
}

