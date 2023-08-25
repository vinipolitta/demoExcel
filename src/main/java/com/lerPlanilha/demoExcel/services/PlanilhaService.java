package com.lerPlanilha.demoExcel.services;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Service
public class PlanilhaService {

    public byte[] gerarPlanilha(List<String> resultadoComparacao) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Resultado Comparação");

        // Cabeçalho da planilha
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("Item");
        headerRow.createCell(1).setCellValue("Existência");

        // Preenchimento da planilha
        for (int i = 0; i < resultadoComparacao.size(); i++) {
            Row dataRow = sheet.createRow(i + 1);
            String resultado = resultadoComparacao.get(i);
            String[] partesResultado = resultado.split(": ");
            String item = partesResultado[1];
            String existencia = partesResultado[0];

            dataRow.createCell(0).setCellValue(item);
            dataRow.createCell(1).setCellValue(existencia);
        }

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        workbook.write(outputStream);
        workbook.close();

        return outputStream.toByteArray();
    }
}
