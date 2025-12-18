package com.LiveStats.LiveStats.modulos.ixc.dto;

import com.LiveStats.LiveStats.modulos.ixc.enums.Status;

public record RequestBody(
        String qtype,
        Status query,
        String oper,
        String page,
        String rp
) {
}
