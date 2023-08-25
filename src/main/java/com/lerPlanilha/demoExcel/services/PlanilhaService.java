package com.lerPlanilha.demoExcel.services;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Service
public class PlanilhaService {

    public byte[] gerarPlanilhaComComparacao(List<String> listaOriginal, List<String> listaApi,
            List<String> resultadoComparacao)
            throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Nova Planilha");

        // Criar o cabeçalho da nova planilha
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("Item Original");
        headerRow.createCell(1).setCellValue("Existência na Outra Lista");

        // Preencher a nova planilha com os dados da original e resultados de comparação
        for (int i = 0; i < listaOriginal.size(); i++) {
            Row dataRow = sheet.createRow(i + 1);
            String itemOriginal = listaOriginal.get(i);
            String resultado = resultadoComparacao.get(i);
            String[] partesResultado = resultado.split(": ");
            String existencia = partesResultado[0];

            dataRow.createCell(0).setCellValue(itemOriginal);
            dataRow.createCell(1).setCellValue(existencia);
        }

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        workbook.write(outputStream);
        workbook.close();

        return outputStream.toByteArray();
    }
}
