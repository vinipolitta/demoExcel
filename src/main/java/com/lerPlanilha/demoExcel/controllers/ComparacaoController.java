package com.lerPlanilha.demoExcel.controllers;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.RestController;

// import com.lerPlanilha.demoExcel.services.ExcelMicroservice;
// import com.lerPlanilha.demoExcel.services.ExternalApiService;
// import com.lerPlanilha.demoExcel.services.FileStorageService;
// import com.lerPlanilha.demoExcel.services.ListaService;

// import javax.servlet.http.HttpServletResponse;
// import java.io.IOException;
// import java.util.List;

// @RestController
// public class ComparacaoController {

//     @Autowired
//     private ListaService listaService;
//     @Autowired
//     private ExcelMicroservice excelMicroservice;
//     @Autowired
//     private ExternalApiService externalApiService;

//     @GetMapping("/comparar-listas")
//     public void compararListas(HttpServletResponse response) throws IOException {
//         // Supondo que você já tem as duas listas obtidas da planilha e da API REST
//         List<String> listaPlanilha = excelMicroservice.getColumnValues(); // Obtenha a lista da planilha
//         List<String> listaApi = externalApiService.getDataFromApi(); // Obtenha a lista da API

//         List<String> resultadoComparacao = listaService.compararListas(listaPlanilha, listaApi);

//         // Aqui você pode criar a nova planilha e escrever os resultados nela
//         // Para simplificar, vou apenas imprimir os resultados no console
//         for (String resultado : resultadoComparacao) {
//             System.out.println(resultado);
//         }

//         // Aqui você pode configurar a resposta para fazer o download da planilha
//         response.setContentType("application/octet-stream");
//         response.setHeader("Content-Disposition", "attachment; filename=nova-planilha.xlsx");

//         // Escreva a planilha no fluxo de saída da resposta (response.getOutputStream())
//         // Implementação depende da biblioteca usada para criar a planilha (por exemplo,
//         // Apache POI, Apache POI-OOXML, etc.)

//         // Feche o fluxo de saída
//         response.flushBuffer();
//     }
// }
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lerPlanilha.demoExcel.services.ExcelMicroservice;
import com.lerPlanilha.demoExcel.services.ExcelService;
import com.lerPlanilha.demoExcel.services.ExternalApiService;
import com.lerPlanilha.demoExcel.services.ListaService;
import com.lerPlanilha.demoExcel.services.PlanilhaService;

import javax.servlet.http.HttpServletResponse;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@RestController
public class ComparacaoController {

    @Autowired
    private PlanilhaService planilhaService;

    @Autowired
    private ListaService listaService;

    @Autowired
    private ExcelMicroservice excelMicroservice;

    @Autowired
    private ExternalApiService externalApiService;

    private static final String SAVE_DIRECTORY = "D:\\TestePlanilha\\";

    @Autowired
    private ExcelService excelService;

    @GetMapping("/adicionar-coluna")
    public String adicionarColunaComparacao() {
        String caminhoArquivoOriginal = "C:\\teste\\teste1 (1).xlsx";
        List<String> listaPlanilha = excelMicroservice.getColumnValues(); // Substitua pela obtenção real dos dados da
                                                                          // planilha
        List<String> listaApi = externalApiService.getDataFromApi(); // Substitua pela obtenção real dos dados da API

        List<String> resultadoComparacao = listaService.compararListas(listaPlanilha, listaApi);

        try {
            excelService.criarNovaPlanilhaComColuna(caminhoArquivoOriginal, resultadoComparacao);
            return "Coluna adicionada com sucesso!";
        } catch (IOException e) {
            return "Erro ao adicionar a coluna: " + e.getMessage();
        }
    }

    @GetMapping("/comparar-listas")
    public String compararListas() throws IOException {
        // Supondo que você já tem as duas listas obtidas da planilha e da API REST
        List<String> listaPlanilha = excelMicroservice.getColumnValues(); // Obtenha a lista da planilha
        List<String> listaApi = externalApiService.getDataFromApi(); // Obtenha a lista da API

        List<String> resultadoComparacao = listaService.compararListas(listaPlanilha, listaApi);

        // Gere a nova planilha com base nos dados originais e resultados de comparação
        byte[] planilhaBytes = planilhaService.gerarPlanilhaComComparacao(listaPlanilha, listaApi, resultadoComparacao);

        // Defina o caminho completo do arquivo
        String caminhoArquivo = SAVE_DIRECTORY + "nova-planilha-comparacao.xlsx";

        // Salve a planilha no diretório especificado
        try (FileOutputStream fos = new FileOutputStream(caminhoArquivo)) {
            fos.write(planilhaBytes);
        }

        return "Planilha salva em: " + caminhoArquivo;
    }
}
