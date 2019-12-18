package ru.rosbank.javaschool.vekback.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.rosbank.javaschool.vekback.entity.ItemEntity;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemResponseDto {
    private int id;
    private String name;
    private int price;
    private int quantity;
    private String image;
    private String description;

    public static ItemResponseDto from(ItemEntity model) {
        return new ItemResponseDto(
                model.getId(),
                model.getName(),
                model.getPrice(),
                model.getQuantity(),
                model.getImage(),
                model.getDescription()
        );
    }
}
