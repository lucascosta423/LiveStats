package com.LiveStats.LiveStats.modulos.ixc.enums;

import java.util.Arrays;

public enum Status {
    S("S"),
    N("N"),
    SS("SS");

    private final String value;

    Status(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static Status from(String raw) {
        if (raw == null || raw.trim().isEmpty()) {
            return SS;
        }

        return Arrays.stream(values())
                .filter(s -> s.value.equalsIgnoreCase(raw.trim()))
                .findFirst()
                .orElse(SS);
    }
}
