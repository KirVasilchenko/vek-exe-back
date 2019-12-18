package ru.rosbank.javaschool.vekback.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.rosbank.javaschool.vekback.entity.OrderPositionEntity;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderPositionResponseDto {
    private int id;
    private int orderId;
    private int productId;
    private String productName;
    private int productPrice;
    private int productQuantity;

    public static OrderPositionResponseDto from(OrderPositionEntity model) {
        return new OrderPositionResponseDto(
                model.getId(),
                model.getOrderId(),
                model.getProductId(),
                model.getProductName(),
                model.getProductPrice(),
                model.getProductQuantity()
        );
    }
}