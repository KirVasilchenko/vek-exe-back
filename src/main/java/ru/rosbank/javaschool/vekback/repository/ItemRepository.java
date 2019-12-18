package ru.rosbank.javaschool.vekback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.rosbank.javaschool.vekback.entity.ItemEntity;

public interface ItemRepository extends JpaRepository<ItemEntity, Integer> {

}
