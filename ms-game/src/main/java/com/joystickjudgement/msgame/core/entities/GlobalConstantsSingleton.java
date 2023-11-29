package com.joystickjudgement.msgame.core.entities;

import java.util.Objects;

public class GlobalConstantsSingleton {

    private static GlobalConstantsSingleton instance;
    public static final String REQUEST_ID_HEADER = "X-Request-Id";
    public static final String GAME_RESOURCE_BASE_PATH = "/games";
    public static final String API_KEY_HEADER = "Api-Key";
    public static final String DEFAULT_ERROR_HANDLING_PATTERN = "Handling: {}, error: {}, stacktrace: {}";


    private GlobalConstantsSingleton() {}

    public GlobalConstantsSingleton getInstance() {
        return Objects.requireNonNullElseGet(instance, GlobalConstantsSingleton::new);
    }

}
