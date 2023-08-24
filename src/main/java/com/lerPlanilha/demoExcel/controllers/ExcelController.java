package com.lerPlanilha.demoExcel.controllers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.lerPlanilha.demoExcel.services.ExcelMicroservice;
import com.lerPlanilha.demoExcel.services.ExcelService;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@RestController
public class ExcelController {

    @Autowired
    private ExcelMicroservice excelService;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadExcel(@RequestParam("file") MultipartFile file,
            @RequestParam("column") int column) {

        // cria a msg de sucesso ou erro
        String message = excelService.uploadExcel(file, column);

        if (message.startsWith("Erro")) {
            return ResponseEntity.badRequest().body(message);
        } else {
            return ResponseEntity.ok(message);
        }
    }

    @GetMapping("/columnValues")
    public ResponseEntity<List<String>> getColumnValues() {
        // Call the service method
        List<String> values = excelService.getColumnValues();
        if (values.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(values);
        }
    }
}