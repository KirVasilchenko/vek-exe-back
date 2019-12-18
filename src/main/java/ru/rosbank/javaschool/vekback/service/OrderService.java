package ru.rosbank.javaschool.vekback.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.rosbank.javaschool.vekback.dto.OrderResponseDto;
import ru.rosbank.javaschool.vekback.dto.OrderSaveRequestDto;
import ru.rosbank.javaschool.vekback.entity.OrderEntity;
import ru.rosbank.javaschool.vekback.repository.OrderRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderService {
  private final OrderRepository repository;

  public List<OrderResponseDto> getAll() {
    return repository.findAll().stream()
        .map(OrderResponseDto::from)
        .collect(Collectors.toList());
  }

  public OrderResponseDto save(OrderSaveRequestDto dto) {
    return OrderResponseDto.from(repository.save(OrderEntity.from(dto)));
  }

  public void removeById(int id) {
    repository.deleteById(id);
  }
}
