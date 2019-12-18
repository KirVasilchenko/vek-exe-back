package ru.rosbank.javaschool.vekback.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.rosbank.javaschool.vekback.dto.OrderPositionSaveRequestDto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class OrderPositionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int orderId;
    private int productId;
    private String productName;
    private int productPrice;
    private int productQuantity;

    public static OrderPositionEntity from(OrderPositionSaveRequestDto dto) {
        return new OrderPositionEntity(
                dto.getId(),
                dto.getOrderId(),
                dto.getProductId(),
                dto.getProductName(),
                dto.getProductPrice(),
                dto.getProductQuantity());
    }
}