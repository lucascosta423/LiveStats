package com.LiveStats.LiveStats.modulos.ixc.dto;

public record RequestBody(
        String qtype,
        String query,
        String oper,
        String page,
        String rp,
        String sortname,
        String sortorder
) {
}
