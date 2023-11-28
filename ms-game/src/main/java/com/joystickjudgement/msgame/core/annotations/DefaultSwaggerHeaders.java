package com.joystickjudgement.msgame.core.annotations;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static com.joystickjudgement.msgame.core.entities.GlobalConstantsSingleton.REQUEST_ID_HEADER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Parameter(
        in = ParameterIn.HEADER,
        name = REQUEST_ID_HEADER,
        required = true
)
@Retention(RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
@Documented
public @interface DefaultSwaggerHeaders {
}
