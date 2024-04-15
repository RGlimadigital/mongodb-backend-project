package com.rodrigolima.anotaaiapi.controllers;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rodrigolima.anotaaiapi.domain.category.Category;
import com.rodrigolima.anotaaiapi.domain.category.CategoryDTO;
import com.rodrigolima.anotaaiapi.services.CategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import java.util.List;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = CategoryController.class)
class CategoryControllerTest {



    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CategoryService categoryService;


    @Autowired
    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void setup(){

        Category category =  new Category();

        category.setId("1");
        category.setTitle("Test Category");

        when(categoryService.findById("1")).thenReturn(category);
        when(categoryService.insert(any(CategoryDTO.class))).thenReturn(category);
        when(categoryService.findAll()).thenReturn(List.of(category));
    }

    @Test
    public void testCreateCategory() throws Exception {
        CategoryDTO categoryDTO = new CategoryDTO("Test Category", "Description", "2");

        mockMvc.perform(post("/api/category")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(categoryDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Test Category"));
    }

    @Test
    public void testFindAllCategories() throws Exception {
        mockMvc.perform(get("/api/category")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Electronics"));
    }

    @Test
    public void testFindCategoryById() throws Exception {
        mockMvc.perform(get("/api/category/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"));
    }

    @Test
    public void testUpdateCategory() throws Exception {
        CategoryDTO categoryDTO = new CategoryDTO("Updated Category", "Description", "2");

        mockMvc.perform(put("/api/category/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(categoryDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Updated Electronics"));
    }

    @Test
    public void testDeleteCategory() throws Exception {
        mockMvc.perform(delete("/api/category/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
}