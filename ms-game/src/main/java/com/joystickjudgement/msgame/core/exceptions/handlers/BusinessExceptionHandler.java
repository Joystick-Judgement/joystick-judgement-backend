package com.joystickjudgement.msgame.core.exceptions.handlers;

import com.joystickjudgement.msgame.core.entities.BusinessResponseError;
import com.joystickjudgement.msgame.core.entities.ResponseError;
import lombok.extern.log4j.Log4j2;
import org.slf4j.MDC;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static com.joystickjudgement.msgame.core.entities.GlobalConstantsSingleton.DEFAULT_ERROR_HANDLING_PATTERN;
import static com.joystickjudgement.msgame.core.entities.GlobalConstantsSingleton.REQUEST_ID_HEADER;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

@ControllerAdvice
@Log4j2
public class BusinessExceptionHandler {

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ResponseError> handleDataIntegrityViolationException(final DataIntegrityViolationException ex) {
        var response = new BusinessResponseError(null, ex.getMessage(), MDC.get(REQUEST_ID_HEADER));
        log.error(DEFAULT_ERROR_HANDLING_PATTERN, ex.getClass().getSimpleName(), ex.getMessage(), ex.getStackTrace());

        return ResponseEntity.status(BAD_REQUEST).body(new ResponseError(response));
    }

}
