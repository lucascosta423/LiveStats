package com.LiveStats.LiveStats.modulos.ixc.dto;

import java.util.List;

public record ReponseLogins(
    List<Logins> registros
) {
    public record Logins(
            String online,
            String id_contrato,
            String login,
            String id_cliente,
            String ultima_conexao_inicial,
            String ultima_conexao_final
            ) {}
}
