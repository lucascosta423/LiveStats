package com.LiveStats.LiveStats.modulos.ixc.dto;

import java.util.List;

public record ResponseClientes(
        String total,
        List<Cliente> registros
) {
}
