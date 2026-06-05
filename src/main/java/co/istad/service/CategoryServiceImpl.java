package co.istad.service;

import co.istad.dto.CategoryRequest;
import co.istad.dto.CategoryResponse;
import co.istad.dto.UpdateCategoryRequest;
import co.istad.entity.Category;
import co.istad.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private Integer nextId = 4;

    private Category mapToEntity(CategoryRequest request) {
        Category category = new Category();
        category.setName(request.name());
        category.setDescription(request.description());
        category.setIsActive(request.isActive());
        return category;
    }

    private CategoryResponse mapToResponse(Category category) {
        return new CategoryResponse(
                category.getId(),
                category.getName(),
                category.getDescription(),
                category.getIsActive()
        );
    }

    @Override
    public CategoryResponse createCategory(CategoryRequest request) {
        var category = mapToEntity(request);
        category.setId(nextId++);
        return mapToResponse(categoryRepository.createCategory(category));
    }

    @Override
    public List<CategoryResponse> findAllCategories() {
        return categoryRepository.getCategoryList()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public CategoryResponse findCategoryById(Integer id) {
        var category = categoryRepository.findCategoryById(id);
        return mapToResponse(category);
    }

    @Override
    public CategoryResponse updateCategory(Integer id, UpdateCategoryRequest request) {
        var existingCategory = categoryRepository.findCategoryById(id);
        if (request.name() != null)
            existingCategory.setName(request.name());
        if (request.description() != null)
            existingCategory.setDescription(request.description());
        if (request.isActive() != null)
            existingCategory.setIsActive(request.isActive());
        categoryRepository.updateCategory(existingCategory);
        return mapToResponse(existingCategory);
    }

    @Override
    public boolean deleteCategory(Integer id) {
        categoryRepository.findCategoryById(id);
        return categoryRepository.deleteCategoryById(id);
    }
}