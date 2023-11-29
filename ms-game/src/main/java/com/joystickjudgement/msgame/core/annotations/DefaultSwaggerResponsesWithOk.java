package com.joystickjudgement.msgame.core.annotations;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.lang.annotation.*;

import static java.net.HttpURLConnection.HTTP_OK;

@DefaultSwaggerHeaders
@ApiResponses(value = {
        @ApiResponse(
                responseCode = "" + HTTP_OK,
                description = "Requisição processada com sucesso."
        )
})
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
@Documented
public @interface DefaultSwaggerResponsesWithOk { }
