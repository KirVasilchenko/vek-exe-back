package ru.rosbank.javaschool.vekback.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.rosbank.javaschool.vekback.dto.ItemResponseDto;
import ru.rosbank.javaschool.vekback.dto.ItemSaveRequestDto;
import ru.rosbank.javaschool.vekback.entity.ItemEntity;
import ru.rosbank.javaschool.vekback.repository.ItemRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class ItemService {
  private final ItemRepository repository;

  public List<ItemResponseDto> getAll() {
    return repository.findAll().stream()
        .map(ItemResponseDto::from)
        .collect(Collectors.toList());
  }

  public ItemResponseDto save(ItemSaveRequestDto dto) {
    return ItemResponseDto.from(repository.save(ItemEntity.from(dto)));
  }

  public void removeById(int id) {
    repository.deleteById(id);
  }
}
