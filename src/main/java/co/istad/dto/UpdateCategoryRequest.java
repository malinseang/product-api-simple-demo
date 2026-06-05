package co.istad.dto;

public record UpdateCategoryRequest(
        String name,
        String description,
        Boolean isActive
) {
}