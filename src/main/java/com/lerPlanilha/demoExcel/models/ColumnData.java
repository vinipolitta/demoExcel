package com.lerPlanilha.demoExcel.models;

import java.util.List;

public class ColumnData {
    private List<String> values;

    public ColumnData(List<String> values) {
        this.values = values;
    }

    public List<String> getValues() {
        return values;
    }

    public void setValues(List<String> values) {
        this.values = values;
    }
}
