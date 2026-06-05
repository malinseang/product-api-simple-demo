package co.istad.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CategoryRequest(
        @NotBlank(message = "name is required")
        String name,
        @NotBlank(message = "description is required")
        String description,
        @NotNull(message = "isActive is required")
        Boolean isActive
) {
}