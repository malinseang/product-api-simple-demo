package co.istad.service;

import co.istad.dto.CategoryRequest;
import co.istad.dto.CategoryResponse;
import co.istad.dto.UpdateCategoryRequest;

import java.util.List;

public interface CategoryService {
    CategoryResponse createCategory(CategoryRequest request);
    List<CategoryResponse> findAllCategories();
    CategoryResponse findCategoryById(Integer id);
    CategoryResponse updateCategory(Integer id, UpdateCategoryRequest request);
    boolean deleteCategory(Integer id);
}