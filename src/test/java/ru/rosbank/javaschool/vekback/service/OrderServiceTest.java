package ru.rosbank.javaschool.vekback.service;

import org.junit.jupiter.api.Test;
import ru.rosbank.javaschool.vekback.dto.OrderResponseDto;
import ru.rosbank.javaschool.vekback.dto.OrderSaveRequestDto;
import ru.rosbank.javaschool.vekback.entity.OrderEntity;
import ru.rosbank.javaschool.vekback.enumeration.OrderStatus;
import ru.rosbank.javaschool.vekback.repository.OrderRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class OrderServiceTest {

    @Test
    void save() {
        OrderRepository repoMock = mock(OrderRepository.class);
        OrderService service = new OrderService(repoMock);
        OrderSaveRequestDto dto = new OrderSaveRequestDto(0, OrderStatus.NEW);
        OrderEntity order = new OrderEntity(0, OrderStatus.NEW);
        when(repoMock.save(order)).thenReturn(order);
        OrderResponseDto expected = new OrderResponseDto(0, OrderStatus.NEW);
        OrderResponseDto actual = service.save(dto);
        assertEquals(expected, actual);
    }

    @Test
    void getAll() {
        OrderRepository repoMock = mock(OrderRepository.class);
        OrderService service = new OrderService(repoMock);
        OrderEntity order = new OrderEntity(0, OrderStatus.NEW);
        List<OrderEntity> list = new ArrayList<>();
        list.add(order);
        when(repoMock.findAll()).thenReturn(list);
        OrderResponseDto dto = new OrderResponseDto(0, OrderStatus.NEW);
        List<OrderResponseDto> listDto = new ArrayList<>();
        listDto.add(dto);
        List<OrderResponseDto> actual = service.getAll();
        assertIterableEquals(actual, listDto);
    }


    @Test
    void removeById() {
        OrderRepository repoMock = mock(OrderRepository.class);
        OrderService service = new OrderService(repoMock);
        OrderEntity order = new OrderEntity(0, OrderStatus.NEW);
        List<OrderEntity> list = new ArrayList<>();
        list.add(order);
        when(repoMock.save(order)).thenReturn(order);
        when(repoMock.findAll()).thenReturn(Collections.emptyList());
        OrderSaveRequestDto dto = new OrderSaveRequestDto(0, OrderStatus.NEW);
        OrderResponseDto dtoReturn = service.save(dto);
        OrderResponseDto dtoMustReturn = new OrderResponseDto(0, OrderStatus.NEW);
        service.removeById(1);
        List<OrderResponseDto> result = service.getAll();
        assertEquals(dtoMustReturn, dtoReturn);
        assertEquals(Collections.emptyList(), result);
    }
}