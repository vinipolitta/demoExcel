package com.lerPlanilha.demoExcel.services;

import org.springframework.stereotype.Service;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@Service
public class ExcelService {

    public String criarNovaPlanilhaComColuna(String caminhoArquivoOriginal, List<String> resultadoComparacao)
            throws IOException {
        FileInputStream fis = new FileInputStream(caminhoArquivoOriginal);
        Workbook workbook = new XSSFWorkbook(fis);
        Sheet sheet = workbook.getSheetAt(0);

        Row headerRow = sheet.getRow(0);
        Cell headerCell = headerRow.createCell(sheet.getRow(0).getLastCellNum());
        headerCell.setCellValue("Existência na Outra Lista");

        int rowNum = 1; // Começar da segunda linha, assumindo que a primeira é o cabeçalho
        for (String resultado : resultadoComparacao) {
            Row dataRow = sheet.getRow(rowNum);
            String[] partesResultado = resultado.split(": ");
            String existencia = partesResultado[0];

            Cell newCell = dataRow.createCell(sheet.getRow(rowNum).getLastCellNum());
            newCell.setCellValue(existencia);

            rowNum++; // Avançar para a próxima linha
        }

        fis.close();

        String caminhoNovaPlanilha = "C:\\teste\\NewPlanilha\\nova-planilha.xlsx"; // Defina o caminho da nova planilha
        FileOutputStream fos = new FileOutputStream(caminhoNovaPlanilha);
        workbook.write(fos);
        fos.close();

        workbook.close();

        return caminhoNovaPlanilha;
    }
}
