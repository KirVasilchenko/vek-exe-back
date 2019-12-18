package ru.rosbank.javaschool.vekback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.rosbank.javaschool.vekback.entity.OrderPositionEntity;

public interface OrderPositionRepository extends JpaRepository<OrderPositionEntity, Integer> {

}
