package com.example.excelserviceapp.services;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.PriorityQueue;

@Service
public class ExcelServiceImpl implements ExcelService {

    @Override
    public Integer findNthMax(String filePath, int n) throws IOException {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(n);

        try (FileInputStream fileInputStream = new FileInputStream(filePath);
                Workbook workbook = new XSSFWorkbook(fileInputStream)) {
            Sheet sheet = workbook.getSheetAt(0);

            for (Row row : sheet) {
                Cell cell = row.getCell(0);
                if (cell != null && cell.getCellType() == CellType.NUMERIC) {
                    minHeap.offer((int) cell.getNumericCellValue());
                    if (minHeap.size() > n) {
                        minHeap.poll();
                    }
                }
            }
        }
        return minHeap.peek();
    }
}