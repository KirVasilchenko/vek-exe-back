package ru.rosbank.javaschool.vekback.service;

import lombok.RequiredArgsConstructor;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.rosbank.javaschool.vekback.dto.ItemResponseDto;
import ru.rosbank.javaschool.vekback.dto.ItemSaveRequestDto;
import ru.rosbank.javaschool.vekback.entity.ItemEntity;
import ru.rosbank.javaschool.vekback.repository.ItemRepository;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

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
        HSSFCell hId = header.createCell(0);
        HSSFCell hName = header.createCell(1);
        HSSFCell hPrice = header.createCell(2);
        HSSFCell hQuantity = header.createCell(3);
        HSSFCell hImage = header.createCell(4);
        HSSFCell hDescription = header.createCell(5);
        hId.setCellValue("ID");
        hName.setCellValue("Название");
        hPrice.setCellValue("Цена");
        hQuantity.setCellValue("Количество");
        hImage.setCellValue("Адрес картинки");
        hDescription.setCellValue("Описание");
        for (ItemEntity entity : repository.findAll()) {
            HSSFRow r = s.createRow(entity.getId());
            HSSFCell cId = r.createCell(0);
            HSSFCell cName = r.createCell(1);
            HSSFCell cPrice = r.createCell(2);
            HSSFCell cQuantity = r.createCell(3);
            HSSFCell cImage = r.createCell(4);
            HSSFCell cDescription = r.createCell(5);
            cId.setCellValue(entity.getId());
            cName.setCellValue(entity.getName());
            cPrice.setCellValue(entity.getPrice());
            cQuantity.setCellValue(entity.getQuantity());
            cImage.setCellValue(entity.getImage());
            cDescription.setCellValue(entity.getDescription());
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
