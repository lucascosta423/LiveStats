package com.LiveStats.LiveStats.dto;

public record DashboardResponse(
        ResponseLoginsFinal online,
        ResponseLoginsFinal offline,
        ResponseLoginsFinal semStatus
) {}

