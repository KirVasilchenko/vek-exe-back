package ru.rosbank.javaschool.vekback.service;

import org.junit.jupiter.api.Test;
import ru.rosbank.javaschool.vekback.dto.OrderPositionResponseDto;
import ru.rosbank.javaschool.vekback.dto.OrderPositionSaveRequestDto;
import ru.rosbank.javaschool.vekback.entity.OrderPositionEntity;
import ru.rosbank.javaschool.vekback.repository.OrderPositionRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class OrderPositionServiceTest {

    @Test
    void save() {
        OrderPositionRepository repoMock = mock(OrderPositionRepository.class);
        OrderPositionService service = new OrderPositionService(repoMock);
        OrderPositionSaveRequestDto dto = new OrderPositionSaveRequestDto(0, 1, 1, "test", 100, 1);
        OrderPositionEntity orderPosition = new OrderPositionEntity(0, 1, 1, "test", 100, 1);
        when(repoMock.save(orderPosition)).thenReturn(orderPosition);
        OrderPositionResponseDto expected = new OrderPositionResponseDto(0, 1, 1, "test", 100, 1);
        OrderPositionResponseDto actual = service.save(dto);
        assertEquals(expected, actual);
    }

    @Test
    void getAll() {
        OrderPositionRepository repoMock = mock(OrderPositionRepository.class);
        OrderPositionService service = new OrderPositionService(repoMock);
        OrderPositionEntity orderPosition = new OrderPositionEntity(0, 1, 1, "test", 100, 1);
        List<OrderPositionEntity> list = new ArrayList<>();
        list.add(orderPosition);
        when(repoMock.findAll()).thenReturn(list);
        OrderPositionResponseDto dto = new OrderPositionResponseDto(0, 1, 1, "test", 100, 1);
        List<OrderPositionResponseDto> listDto = new ArrayList<>();
        listDto.add(dto);
        List<OrderPositionResponseDto> actual = service.getAll();
        assertIterableEquals(actual, listDto);
    }


    @Test
    void removeById() {
        OrderPositionRepository repoMock = mock(OrderPositionRepository.class);
        OrderPositionService service = new OrderPositionService(repoMock);
        OrderPositionEntity orderPosition = new OrderPositionEntity(0, 1, 1, "test", 100, 1);
        List<OrderPositionEntity> list = new ArrayList<>();
        list.add(orderPosition);
        when(repoMock.save(orderPosition)).thenReturn(orderPosition);
        when(repoMock.findAll()).thenReturn(Collections.emptyList());
        OrderPositionSaveRequestDto dto = new OrderPositionSaveRequestDto(0, 1, 1, "test", 100, 1);
        OrderPositionResponseDto dtoReturn = service.save(dto);
        OrderPositionResponseDto dtoMustReturn = new OrderPositionResponseDto(0, 1, 1, "test", 100, 1);
        service.removeById(1);
        List<OrderPositionResponseDto> result = service.getAll();
        assertEquals(dtoMustReturn, dtoReturn);
        assertEquals(Collections.emptyList(), result);
    }
}