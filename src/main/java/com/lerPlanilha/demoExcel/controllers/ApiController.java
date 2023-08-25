package com.lerPlanilha.demoExcel.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lerPlanilha.demoExcel.services.ExternalApiService;

@RestController
@RequestMapping("/api")
public class ApiController {
    @Autowired
    private ExternalApiService apiService;

    @GetMapping("/consume-api")
    public List<String> consumeApi() {
        return apiService.getDataFromApi();
    }
}
