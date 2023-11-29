package com.joystickjudgement.msgame.core.exceptions.handlers;

import com.joystickjudgement.msgame.core.entities.BusinessResponseError;
import com.joystickjudgement.msgame.core.entities.ResponseError;
import com.joystickjudgement.msgame.core.exceptions.InternalServerErrorHttpException;
import lombok.extern.log4j.Log4j2;
import org.slf4j.MDC;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static com.joystickjudgement.msgame.core.entities.GlobalConstantsSingleton.REQUEST_ID_HEADER;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@ControllerAdvice
@Log4j2
public class HttpExceptionHandler {

    @ExceptionHandler(InternalServerErrorHttpException.class)
    public ResponseEntity<ResponseError> handleInternalServerErrorHttpException(InternalServerErrorHttpException ex) {
        var response = new BusinessResponseError(null, ex.getMessage(), MDC.get(REQUEST_ID_HEADER));
        log.error("Handling internal server error: {} ", ex.getMessage());
        return new ResponseEntity<>(new ResponseError(response), INTERNAL_SERVER_ERROR);
    }

}
