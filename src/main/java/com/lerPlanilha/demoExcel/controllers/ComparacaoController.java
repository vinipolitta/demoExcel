package com.lerPlanilha.demoExcel.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lerPlanilha.demoExcel.services.ExcelMicroservice;
import com.lerPlanilha.demoExcel.services.ExternalApiService;
import com.lerPlanilha.demoExcel.services.FileStorageService;
import com.lerPlanilha.demoExcel.services.ListaService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
public class ComparacaoController {

    @Autowired
    private ListaService listaService;
    @Autowired
    private ExcelMicroservice excelMicroservice;
    @Autowired
    private ExternalApiService externalApiService;

    @GetMapping("/comparar-listas")
    public void compararListas(HttpServletResponse response) throws IOException {
        // Supondo que você já tem as duas listas obtidas da planilha e da API REST
        List<String> listaPlanilha = excelMicroservice.getColumnValues(); // Obtenha a lista da planilha
        List<String> listaApi = externalApiService.getDataFromApi(); // Obtenha a lista da API

        List<String> resultadoComparacao = listaService.compararListas(listaPlanilha, listaApi);

        // Aqui você pode criar a nova planilha e escrever os resultados nela
        // Para simplificar, vou apenas imprimir os resultados no console
        for (String resultado : resultadoComparacao) {
            System.out.println(resultado);
        }

        // Aqui você pode configurar a resposta para fazer o download da planilha
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment; filename=nova-planilha.xlsx");

        // Escreva a planilha no fluxo de saída da resposta (response.getOutputStream())
        // Implementação depende da biblioteca usada para criar a planilha (por exemplo,
        // Apache POI, Apache POI-OOXML, etc.)

        // Feche o fluxo de saída
        response.flushBuffer();
    }
}
