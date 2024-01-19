package com.kroll;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class OrderTest {

    @Test
    public void testCreateOrderWithMock() {
        List<Product> products = new ArrayList<>();
        Order order = new Order(1, products, 10);

        assertEquals(1, order.getOrderId());
        assertEquals(products, order.getProductsList());
        assertEquals(10, order.getStatus());
    }


    @Test
    public void testDecrementStatusWithMock() {
        Order order = new Order(1, new ArrayList<>(), 5);

        order.decrementStatus();

        assertEquals(4, order.getStatus());
    }

}

