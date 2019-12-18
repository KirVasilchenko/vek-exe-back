package ru.rosbank.javaschool.vekback.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.rosbank.javaschool.vekback.dto.OrderSaveRequestDto;
import ru.rosbank.javaschool.vekback.enumeration.OrderStatus;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private OrderStatus status;

    public static OrderEntity from(OrderSaveRequestDto dto) {
        return new OrderEntity(dto.getId(), dto.getStatus());
    }
}
