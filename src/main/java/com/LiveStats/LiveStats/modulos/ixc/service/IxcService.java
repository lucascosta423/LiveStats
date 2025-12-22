package com.LiveStats.LiveStats.modulos.ixc.service;

import com.LiveStats.LiveStats.modulos.ixc.dto.*;
import com.LiveStats.LiveStats.modulos.ixc.enums.Status;
import com.LiveStats.LiveStats.utils.JsonMapperUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class IxcService {
    private final WebClient webClient;
    private final JsonMapperUtil jsonMapperUtil;

    public IxcService(WebClient webClient, JsonMapperUtil jsonMapperUtil) {
        this.webClient = webClient;
        this.jsonMapperUtil = jsonMapperUtil;
    }


    public ResponseLogins getAllLogins() {
        String response = webClient.post()
                .uri("/radusuarios")
                .bodyValue(
                        new RequestBody(
                                "radusuarios.ativo",
                                Status.S,
                                "=",
                                "1",
                                getTotal("radusuarios","radusuarios.ativo", Status.S).total()
                        )
                )
                .retrieve()
                .bodyToMono(String.class)
                .block();

        return jsonMapperUtil.fromJson(response, ResponseLogins.class);
    }

    public ResponseClientes getAllClientsByStatus(Status status) {
        String response = webClient.post()
                .uri("/cliente")
                .bodyValue(
                        new RequestBody(
                                "cliente.ativo",
                                status,
                                "=",
                                "1",
                                getTotal("cliente","cliente.ativo", status).total()
                        )
                )
                .retrieve()
                .bodyToMono(String.class)
                .block();

        return jsonMapperUtil.fromJson(response, ResponseClientes.class);
    }


    public ResponseTotal getTotal(String uri, String qtype, Status status){
        String response = webClient.post()
                .uri(uri)
                .bodyValue(
                        new RequestTotal(
                                qtype,
                                status,
                                "="
                        )
                )
                .retrieve()
                .bodyToMono(String.class)
                .block();

        return jsonMapperUtil.fromJson(response,ResponseTotal.class);
    }
}
