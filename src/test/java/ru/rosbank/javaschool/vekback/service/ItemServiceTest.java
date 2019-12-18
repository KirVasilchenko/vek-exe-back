package ru.rosbank.javaschool.vekback.service;

import org.junit.jupiter.api.Test;
import ru.rosbank.javaschool.vekback.dto.ItemResponseDto;
import ru.rosbank.javaschool.vekback.dto.ItemSaveRequestDto;
import ru.rosbank.javaschool.vekback.entity.ItemEntity;
import ru.rosbank.javaschool.vekback.repository.ItemRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ItemServiceTest {

    @Test
    void save() {
        ItemRepository repoMock = mock(ItemRepository.class);
        ItemService service = new ItemService(repoMock);
        ItemSaveRequestDto dto = new ItemSaveRequestDto(0, "name", 100, 1, "", "");
        ItemEntity item = new ItemEntity(0, "name", 100, 1, "", "");
        when(repoMock.save(item)).thenReturn(item);
        ItemResponseDto expected = new ItemResponseDto(0, "name", 100, 1, "", "");
        ItemResponseDto actual = service.save(dto);
        assertEquals(expected, actual);
    }

    @Test
    void getAll() {
        ItemRepository repoMock = mock(ItemRepository.class);
        ItemService service = new ItemService(repoMock);
        ItemEntity item = new ItemEntity(0, "name", 100, 1, "", "");
        List<ItemEntity> list = new ArrayList<>();
        list.add(item);
        when(repoMock.findAll()).thenReturn(list);
        ItemResponseDto dto = new ItemResponseDto(0, "name", 100, 1, "", "");
        List<ItemResponseDto> listDto = new ArrayList<>();
        listDto.add(dto);
        List<ItemResponseDto> actual = service.getAll();
        assertIterableEquals(actual, listDto);
    }


    @Test
    void removeById() {
        ItemRepository repoMock = mock(ItemRepository.class);
        ItemService service = new ItemService(repoMock);
        ItemEntity item = new ItemEntity(0, "name", 100, 1, "", "");
        List<ItemEntity> list = new ArrayList<>();
        list.add(item);
        when(repoMock.save(item)).thenReturn(item);
        when(repoMock.findAll()).thenReturn(Collections.emptyList());
        ItemSaveRequestDto dto = new ItemSaveRequestDto(0, "name", 100, 1, "", "");
        ItemResponseDto dtoReturn = service.save(dto);
        ItemResponseDto dtoMustReturn = new ItemResponseDto(0, "name", 100, 1, "", "");
        service.removeById(1);
        List<ItemResponseDto> result = service.getAll();
        assertEquals(dtoMustReturn, dtoReturn);
        assertEquals(Collections.emptyList(), result);
    }
}