package com.lerPlanilha.demoExcel.services;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ListaService {

    public List<String> compararListas(List<String> listaPlanilha, List<String> listaApi) {
        List<String> resultado = new ArrayList<>();

        for (String itemPlanilha : listaPlanilha) {
            if (listaApi.contains(itemPlanilha)) {
                resultado.add("EXISTE: " + itemPlanilha);
            } else {
                resultado.add("NAO EXISTE: " + itemPlanilha);
            }
        }

        return resultado;
    }
}
