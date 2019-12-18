package ru.rosbank.javaschool.vekback.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemSaveRequestDto {
    private int id;
    private String name;
    private int price;
    private int quantity;
    private String image;
    private String description;
}
