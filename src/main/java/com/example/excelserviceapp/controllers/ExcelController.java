package com.example.excelserviceapp.controllers;

import com.example.excelserviceapp.services.ExcelService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api")
@Tag(name = "Excel Controller", description = "Контроллер для работы с Excel файлами")
public class ExcelController {

    private final ExcelService excelService;

    public ExcelController(ExcelService excelService) {
        this.excelService = excelService;
    }

    @GetMapping
    @Operation(summary = "Найти N-ное максимальное число в файле Excel")
    public ResponseEntity<?> getNthMaxNumber(
            @Parameter(description = "Путь к файлу Excel") @RequestParam String filePath,
            @Parameter(description = "N-ное максимальное число из файла") @RequestParam int n) {
        if (n <= 0) {
            return ResponseEntity.badRequest().body("Параметр n должен быть больше 0");
        }
        try {
            return ResponseEntity.ok(excelService.findNthMax(filePath, n));
        } catch (IOException e) {
            return ResponseEntity.internalServerError()
                    .body("Ошибка при чтении файла: " + e.getMessage());
        }
    }
}