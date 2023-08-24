package com.lerPlanilha.demoExcel.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.web.multipart.MultipartFile;

import com.lerPlanilha.demoExcel.models.SheetData;

public class ExcelService {
    public List<SheetData> getColumnData(MultipartFile file, int columnIndex) throws IOException {
        List<SheetData> columnData = new ArrayList<>();
        Workbook workbook = WorkbookFactory.create(file.getInputStream());
        Sheet sheet = workbook.getSheetAt(0);

        Iterator<Row> rowIterator = sheet.iterator();
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            Cell cell = row.getCell(columnIndex);
            if (cell != null) {
                columnData.add(new SheetData(cell.toString()));
            }
        }

        workbook.close();
        return columnData;
    }
}
