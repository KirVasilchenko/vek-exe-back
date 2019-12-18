package ru.rosbank.javaschool.vekback.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.rosbank.javaschool.vekback.entity.OrderEntity;
import ru.rosbank.javaschool.vekback.enumeration.OrderStatus;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponseDto {
  private int id;
  private OrderStatus status;

  public static OrderResponseDto from(OrderEntity model) {
    return new OrderResponseDto(
        model.getId(),
        model.getStatus()
    );
  }
}
