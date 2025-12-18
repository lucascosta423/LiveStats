package com.LiveStats.LiveStats.modulos.ixc.dto;

import com.LiveStats.LiveStats.dto.LoginsStats;

import java.util.List;

public record ResponseLogins(
    String total,
    List<Login> registros
) {
}
