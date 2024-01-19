package com.kroll;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class ProductTest {

    @Test
    public void testCreateAvailableProducts() {
        List<Product> availableProducts = Product.createAvailableProducts();
        assertNotNull(availableProducts);
        assertEquals(5, availableProducts.size());
    }
}

