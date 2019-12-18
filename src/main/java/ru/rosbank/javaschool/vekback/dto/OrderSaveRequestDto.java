package ru.rosbank.javaschool.vekback.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.rosbank.javaschool.vekback.enumeration.OrderStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderSaveRequestDto {
    private int id;
    private OrderStatus status;
}
