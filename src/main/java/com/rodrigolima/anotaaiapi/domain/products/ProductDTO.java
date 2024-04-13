package com.rodrigolima.anotaaiapi.domain.products;

import com.rodrigolima.anotaaiapi.domain.category.Category;

public record ProductDTO(String title, String description,String onwnerId, Integer price, String categoryId) {
}
