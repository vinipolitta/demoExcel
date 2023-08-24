package com.lerPlanilha.demoExcel.models;

public class SheetData {
    private String columnValue;

    public SheetData(String columnValue) {
        this.columnValue = columnValue;
    }

    public String getColumnValue() {
        return columnValue;
    }

    public void setColumnValue(String columnValue) {
        this.columnValue = columnValue;
    }
}
