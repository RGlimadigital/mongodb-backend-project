package com.rodrigolima.anotaaiapi.domain.category;

import static org.junit.jupiter.api.Assertions.*;

class CategoryTest {

    public void testCategoryConstructor(){

        CategoryDTO categoryDTOMock = new CategoryDTO("Test Title", "Description Test", "id34544556");

        Category categoryMock = new Category(categoryDTOMock);

        assertEquals("Test Title", categoryMock.getTitle());
        assertEquals("Description Test", categoryMock.getDescription());
        assertEquals("id34544556", categoryMock.getOwnerId());
    }

}