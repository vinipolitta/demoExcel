package com.lerPlanilha.demoExcel.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ExternalApiService {
    // private final String apiUrl = "URL_DA_API_AQUI";

    // public String getDataFromApi() {
    // RestTemplate restTemplate = new RestTemplate();
    // return restTemplate.getForObject(apiUrl, String.class);
    // }

    private final String apiUrl = "http://localhost:8080/api/lista-de-strings";

    public List<String> getDataFromApi() {
        RestTemplate restTemplate = new RestTemplate();
        List<String> response = restTemplate.getForObject(apiUrl, List.class); // Alteração aqui
        return response;
    }
}
