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

    public void adicionarColunaComparacao(String caminhoArquivoOriginal, List<String> resultadoComparacao)
            throws IOException {
        FileInputStream fis = new FileInputStream(caminhoArquivoOriginal);
        Workbook workbook = new XSSFWorkbook(fis);
        Sheet sheet = workbook.getSheetAt(0); // Supondo que a planilha está na primeira aba

        // Adicionar um cabeçalho para a nova coluna
        Row headerRow = sheet.getRow(0);
        Cell headerCell = headerRow.createCell(sheet.getRow(0).getLastCellNum());
        headerCell.setCellValue("Existência na Outra Lista");

        // Preencher a nova coluna com os resultados
        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            Row dataRow = sheet.getRow(i);
            String resultado = resultadoComparacao.get(i - 1);
            String[] partesResultado = resultado.split(": ");
            String existencia = partesResultado[0];

            Cell newCell = dataRow.createCell(sheet.getRow(i).getLastCellNum());
            newCell.setCellValue(existencia);
        }

        fis.close();

        // Salvar o arquivo modificado
        FileOutputStream fos = new FileOutputStream(caminhoArquivoOriginal);
        workbook.write(fos);
        fos.close();

        workbook.close();
    }
}
