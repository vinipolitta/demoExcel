package com.lerPlanilha.demoExcel.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lerPlanilha.demoExcel.models.MockedData;

@RestController
@RequestMapping("/api")
public class MockedListController {

    @GetMapping("/lista-de-strings")
    public ResponseEntity<List<String>> getStringList() {
        List<String> stringList = new ArrayList<>();
        stringList.add("item1");
        stringList.add("Item2");
        stringList.add("item3");
        stringList.add("item4");
        stringList.add("item5");

        return ResponseEntity.ok(stringList);
    }
}
