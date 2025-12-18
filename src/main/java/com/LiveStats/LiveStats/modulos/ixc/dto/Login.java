package com.LiveStats.LiveStats.modulos.ixc.dto;

public record Login(
        String online,
        String login,
        String endereco,
        String id_cliente,
        String ultima_conexao_inicial,
        String ultima_conexao_final
) {}
