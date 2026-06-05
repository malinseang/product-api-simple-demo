package co.istad.dto;

public record ProductResponse(
        Integer id,
        String name,
        String description,
        Float price
) {
}
