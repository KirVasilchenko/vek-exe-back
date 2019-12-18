package ru.rosbank.javaschool.vekback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.rosbank.javaschool.vekback.entity.OrderEntity;

public interface OrderRepository extends JpaRepository<OrderEntity, Integer> {

}
