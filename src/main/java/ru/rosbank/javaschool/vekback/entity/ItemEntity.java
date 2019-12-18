package ru.rosbank.javaschool.vekback.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.rosbank.javaschool.vekback.dto.ItemSaveRequestDto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class ItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private int price;
    private int quantity;
    private String image;
    private String description;

    public static ItemEntity from(ItemSaveRequestDto dto) {
        return new ItemEntity(dto.getId(), dto.getName(), dto.getPrice(), dto.getQuantity(), dto.getImage(), dto.getDescription());
    }
}
