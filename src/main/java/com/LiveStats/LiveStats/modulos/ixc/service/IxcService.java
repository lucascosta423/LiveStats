package com.LiveStats.LiveStats.modulos.ixc.service;

import com.LiveStats.LiveStats.modulos.ixc.dto.RequestTotal;
import com.LiveStats.LiveStats.modulos.ixc.dto.ResponseTotal;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import tools.jackson.databind.ObjectMapper;

@Service
public class IxcService {
    private final WebClient webClient;

    public IxcService(WebClient webClient) {
        this.webClient = webClient;
    }


    public ResponseTotal getAllActiveCustomers() {
        String response = webClient.post()
                .uri("/cliente")
                .headers(headers -> headers.add("ixcsoft", "listar"))
                .bodyValue(new RequestTotal("cliente.ativo","S","="))
                .retrieve()
                .bodyToMono(String.class)
                .block();

        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(response, ResponseTotal.class);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao converter resposta IXC", e);
        }
    }
}
