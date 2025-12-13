package com.LiveStats.LiveStats.modulos.ixc.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Configuration
public class WebClientConfig {

    @Value("${ixc.baseUrl}")
    private String BASE_URL;

    @Value("${ixc.id-user}")
    private String ID_USER;

    @Value("${ixc.token}")
    private String TOKEN;

    @Bean
    public WebClient webClient(WebClient.Builder builder) {
        return builder
                .baseUrl(BASE_URL)
                .defaultHeaders(h -> {
                    h.setBasicAuth(ID_USER, TOKEN);
                    h.set("ixcsoft", "listar");
                    h.setAccept(List.of(
                            MediaType.APPLICATION_JSON,
                            MediaType.valueOf("text/x-json")
                    ));
                    h.setContentType(MediaType.APPLICATION_JSON);
                })
                .build();
    }

}

