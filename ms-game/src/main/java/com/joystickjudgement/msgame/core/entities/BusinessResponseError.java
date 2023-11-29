package com.joystickjudgement.msgame.core.entities;

public record BusinessResponseError(
        BusinessErrorCodes businessCode,
        String message,
        String requestId
) {}
