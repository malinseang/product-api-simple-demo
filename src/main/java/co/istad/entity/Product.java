package co.istad.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Product{
    private Integer id;
    private String name;
    private String description;
    private Float price;
    private Integer userId;
}