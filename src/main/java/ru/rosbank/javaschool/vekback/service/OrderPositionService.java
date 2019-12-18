package ru.rosbank.javaschool.vekback.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.rosbank.javaschool.vekback.dto.OrderPositionResponseDto;
import ru.rosbank.javaschool.vekback.dto.OrderPositionSaveRequestDto;
import ru.rosbank.javaschool.vekback.entity.OrderPositionEntity;
import ru.rosbank.javaschool.vekback.repository.OrderPositionRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderPositionService {
  private final OrderPositionRepository repository;

  public List<OrderPositionResponseDto> getAll() {
    return repository.findAll().stream()
        .map(OrderPositionResponseDto::from)
        .collect(Collectors.toList());
  }

  public OrderPositionResponseDto save(OrderPositionSaveRequestDto dto) {
    return OrderPositionResponseDto.from(repository.save(OrderPositionEntity.from(dto)));
  }

  public void removeById(int id) {
    repository.deleteById(id);
  }
}
