package co.istad.repository;

import co.istad.entity.Category;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Repository
public class CategoryRepository {

    private final List<Category> categoryList = new ArrayList<>() {{
        add(new Category(1, "Electronics", "Devices and gadgets", true));
        add(new Category(2, "Clothing", "Apparel and accessories", true));
        add(new Category(3, "Food & Beverages", "Edible products and drinks", true));
    }};

    public List<Category> getCategoryList() {
        return categoryList;
    }

    public Category createCategory(Category category) {
        categoryList.add(category);
        return category;
    }

    public Category findCategoryById(Integer id) {
        return categoryList.stream()
                .filter(category -> category.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Category with ID=" + id + " Not Found"));
    }

    public Category updateCategory(Category updatedCategory) {
        for (int i = 0; i < categoryList.size(); i++) {
            var category = categoryList.get(i);
            if (category.getId().equals(updatedCategory.getId())) {
                categoryList.set(i, updatedCategory);
                return updatedCategory;
            }
        }
        return null;
    }

    public boolean deleteCategoryById(Integer id) {
        return categoryList.removeIf(category -> category.getId().equals(id));
    }
}