package ru.rosbank.javaschool.vekback.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderPositionSaveRequestDto {
    private int id;
    private int orderId;
    private int productId;
    private String productName;
    private int productPrice;
    private int productQuantity;
}