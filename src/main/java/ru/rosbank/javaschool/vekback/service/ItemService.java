package ru.rosbank.javaschool.vekback.service;

import lombok.RequiredArgsConstructor;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.rosbank.javaschool.vekback.dto.ItemResponseDto;
import ru.rosbank.javaschool.vekback.dto.ItemSaveRequestDto;
import ru.rosbank.javaschool.vekback.entity.ItemEntity;
import ru.rosbank.javaschool.vekback.repository.ItemRepository;

import java.io.FileOutputStream;
import java.io.IOException;
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

    public void getPriceSheets() {
        HSSFWorkbook wb = new HSSFWorkbook();
        String filename = "PriceSheets.xls";
        HSSFSheet s = wb.createSheet();

        HSSFRow header = s.createRow(0);
        HSSFCell headerId = header.createCell(0);
        headerId.setCellValue("ID");
        HSSFCell headerName = header.createCell(1);
        headerName.setCellValue("Название");
        HSSFCell headerPrice = header.createCell(2);
        headerPrice.setCellValue("Цена");
        HSSFCell headerQuantity = header.createCell(3);
        headerQuantity.setCellValue("Количество");
        HSSFCell headerImage = header.createCell(4);
        headerImage.setCellValue("Адрес картинки");
        HSSFCell headerDescription = header.createCell(5);
        headerDescription.setCellValue("Описание");
        for (ItemEntity entity : repository.findAll()) {
            HSSFRow r = s.createRow(entity.getId());
            HSSFCell cellId = r.createCell(0);
            cellId.setCellValue(entity.getId());
            HSSFCell cellName = r.createCell(1);
            cellName.setCellValue(entity.getName());
            HSSFCell cellPrice = r.createCell(2);
            cellPrice.setCellValue(entity.getPrice());
            HSSFCell cellQuantity = r.createCell(3);
            cellQuantity.setCellValue(entity.getQuantity());
            HSSFCell cellImage = r.createCell(4);
            cellImage.setCellValue(entity.getImage());
            HSSFCell cellDescription = r.createCell(5);
            cellDescription.setCellValue(entity.getDescription());
        }
        try {
            FileOutputStream out = new FileOutputStream(filename);
            wb.write(out);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}