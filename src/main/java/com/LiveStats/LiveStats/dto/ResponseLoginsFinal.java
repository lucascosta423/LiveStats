package com.LiveStats.LiveStats.dto;

import java.util.List;

public record ResponseLoginsFinal(
        Integer total,
        List<LoginsStats> logins
) {

}
