package ru.rosbank.javaschool.vekback.rest;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import ru.rosbank.javaschool.vekback.dto.ItemResponseDto;
import ru.rosbank.javaschool.vekback.dto.ItemSaveRequestDto;
import ru.rosbank.javaschool.vekback.service.ItemService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/items")
public class RestItemController {
  private final ItemService itemService;
  private final Logger logger = LoggerFactory.getLogger(RestItemController.class);

  @GetMapping
  public List<ItemResponseDto> getAll() {
    logger.info(Thread.currentThread().getName());
    return itemService.getAll();
  }

  @GetMapping("/price")
  public void getPriceSheets() {
    logger.info(Thread.currentThread().getName());
    itemService.getPriceSheets();
  }

  @PostMapping
  public ItemResponseDto save(@RequestBody ItemSaveRequestDto dto) {
    return itemService.save(dto);
  }

  @DeleteMapping("/{id}")
  public void removeById(@PathVariable int id) {
    itemService.removeById(id);
  }
}