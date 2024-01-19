package com.kroll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.*;

public class CartTest {

    @Test
    public void testAddProductToCartWithMock() {
        Cart cart = Mockito.spy(new Cart());
        Product product = mock(Product.class);

        cart.addProduct(product);

        verify(cart, times(1)).addProduct(product);
    }

    @Test
    public void testRemoveProductFromCartWithMock() {
        Cart cart = Mockito.spy(new Cart());
        Product product = mock(Product.class);

        cart.addProduct(product);
        verify(cart, times(1)).addProduct(product);

        cart.removeProduct(product);

        verify(cart, times(1)).removeProduct(product);
    }

}
