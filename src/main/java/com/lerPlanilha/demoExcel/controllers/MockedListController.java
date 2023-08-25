package com.lerPlanilha.demoExcel.controllers;

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

    @GetMapping("/mocked-list")
    public ResponseEntity<List<MockedData>> getMockedList() {
        List<String> mockedData = Arrays.asList("item1", "Item2", "item3", "item4", "item5", "item6");
        MockedData data = new MockedData(mockedData);
        return ResponseEntity.ok(Collections.singletonList(data));
    }
}
