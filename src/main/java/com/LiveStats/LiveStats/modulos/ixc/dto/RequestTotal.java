package com.LiveStats.LiveStats.modulos.ixc.dto;

import com.LiveStats.LiveStats.modulos.ixc.enums.Status;

public record RequestTotal(
        String qtype,
        Status query,
        String oper
) {
}
