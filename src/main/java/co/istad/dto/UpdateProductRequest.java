package co.istad.dto;

public record UpdateProductRequest (
        String name,
        String description,
        Float price
){}
