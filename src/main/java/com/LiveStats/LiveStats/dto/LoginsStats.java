package com.LiveStats.LiveStats.dto;

public record LoginsStats(
        String online,
        String login,
        String endereco,
        String id_cliente,
        String ultima_conexao_inicial,
        String ultima_conexao_final
) {
}
