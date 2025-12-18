package com.LiveStats.LiveStats.dto;

import java.util.List;

public record ResponseLoginsFinal(
        String total,
        List<LoginsStats> logins
) {

}
