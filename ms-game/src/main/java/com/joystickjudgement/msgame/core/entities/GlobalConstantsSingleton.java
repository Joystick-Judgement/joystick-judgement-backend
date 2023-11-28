package com.joystickjudgement.msgame.core.entities;

import java.util.Objects;

public class GlobalConstantsSingleton {

    private static GlobalConstantsSingleton instance;
    public static final String REQUEST_ID_HEADER = "X-Request-Id";
    public static final String API_KEY_HEADER = "Api-Key";
    public static final String HTTP_OK_CODE = "200";
    public static final String HTTP_CREATED_CODE = "201";
    public static final String HTTP_BAD_REQUEST_CODE = "404";
    public static final String SC_UNPROCESSABLE_ENTITY_CODE = "422";
    public static final String HTTP_UNAUTHORIZED_CODE = "401";
    public static final String HTTP_NOT_FOUND_CODE = "404";
    public static final String HTTP_CONFLICT_CODE = "409";
    public static final String SC_REQUEST_URI_TOO_LONG_CODE = "414";
    public static final String HTTP_INTERNAL_ERROR_CODE = "500";
    public static final String HTTP_NOT_IMPLEMENTED_CODE = "501";
    public static final String SC_SERVICE_UNAVAILABLE_CODE = "503";
    public static final String HTTP_GATEWAY_TIMEOUT_CODE = "504";

    private GlobalConstantsSingleton() {}

    public GlobalConstantsSingleton getInstance() {
        return Objects.requireNonNullElseGet(instance, GlobalConstantsSingleton::new);
    }

}
