package com.lerPlanilha.demoExcel.services;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@RestController
public class ExcelMicroservice {

    private List<String> columnValues = new ArrayList<>();

    @PostMapping("/upload")
    public String uploadExcel(@RequestParam("file") MultipartFile file) {
        try {

            // Ler o arquivo Excel enviado
            Workbook workbook = new XSSFWorkbook(file.getInputStream());
            Sheet sheet = workbook.getSheetAt(0);

            // Define o índice da coluna para leitura (por exemplo, a coluna A é o índice 0)
            int columnIndex = 0;

            // Limpa os valores anteriores
            columnValues.clear();

            // Comece a ler a partir da segunda linha para pular a linha do título
            for (int rowIndex = 1; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
                Row row = sheet.getRow(rowIndex);
                if (row != null) {
                    Cell cell = row.getCell(columnIndex);
                    if (cell != null) {
                        columnValues.add(cell.toString());
                    }
                }
            }

            workbook.close();
            return "Planilha inserida com sucesso!";
        } catch (IOException e) {
            e.printStackTrace();
            return "Erro ao inserir a planilha.";
        }
    }

    @GetMapping("/columnValues")
    public List<String> getColumnValues() {
        return columnValues;
    }
}
