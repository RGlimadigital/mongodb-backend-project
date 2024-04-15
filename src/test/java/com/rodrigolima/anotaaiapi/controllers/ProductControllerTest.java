package com.rodrigolima.anotaaiapi.controllers;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rodrigolima.anotaaiapi.domain.products.Product;
import com.rodrigolima.anotaaiapi.domain.products.ProductDTO;
import com.rodrigolima.anotaaiapi.services.ProductService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;



    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void setup(){
        Product product = new Product();
        product.setId("1");
        product.setTitle("Test Product");

        when(productService.findById("1")).thenReturn(product);
        when(productService.insertProduct(any(ProductDTO.class))).thenReturn(product);
        when(productService.findAll()).thenReturn(List.of(product));
    }

    @Test
    public void testCreateProduct() throws Exception {
        ProductDTO productDTO = new ProductDTO("Test Product", "Description", "ownerId", 100, "1");

        mockMvc.perform(post("/api/product")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(productDTO)))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$.title").value("Test Product"));
    }


    @Test
    public void testFindAll() throws Exception {
        mockMvc.perform(get("/api/product")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value("1"));
    }

    @Test
    public void testFindById() throws Exception {
        mockMvc.perform(get("/api/product/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"));
    }

    @Test
    public void testDeleteProduct() throws Exception {
        mockMvc.perform(delete("/api/product/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
}