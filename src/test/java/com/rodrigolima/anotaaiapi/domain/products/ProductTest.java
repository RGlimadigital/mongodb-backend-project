package com.rodrigolima.anotaaiapi.domain.products;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {

    @Test
    public void testProductConstructor() {

        ProductDTO productDTO = new ProductDTO("Test Title", "Test Description", "Test Owner", 1000, "123543");

        Product product = new Product(productDTO);

        assertEquals("Test Title", product.getTitle());
        assertEquals("Test Description", product.getDescription());
        assertEquals("Test Owner", product.getOwnerId());
        assertEquals(Integer.valueOf(1000), product.getPrice());

    }

}