package com.joystickjudgement.msgame.core.exceptions.handlers;

import com.joystickjudgement.msgame.core.entities.BusinessResponseError;
import com.joystickjudgement.msgame.core.entities.ResponseError;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.log4j.Log4j2;
import org.slf4j.MDC;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.stream.Collectors;

import static com.joystickjudgement.msgame.core.entities.GlobalConstantsSingleton.DEFAULT_ERROR_HANDLING_PATTERN;
import static com.joystickjudgement.msgame.core.entities.GlobalConstantsSingleton.REQUEST_ID_HEADER;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

@ControllerAdvice
@Log4j2
public class RequestExceptionHandler {

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ResponseError> handleMissingServletRequestParameterException(final MissingServletRequestParameterException ex) {
        var response = new BusinessResponseError(null, ex.getMessage(), MDC.get(REQUEST_ID_HEADER));
        log.error(DEFAULT_ERROR_HANDLING_PATTERN, ex.getClass().getSimpleName(), ex.getMessage(), ex.getStackTrace());
        return new ResponseEntity<>(new ResponseError(response), BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ResponseError> handleMethodArgumentTypeMismatchException(final MethodArgumentTypeMismatchException ex) {
        var response = new BusinessResponseError(null, ex.getMessage(), MDC.get(REQUEST_ID_HEADER));
        log.error(DEFAULT_ERROR_HANDLING_PATTERN, ex.getClass().getSimpleName(), ex.getMessage(), ex.getStackTrace());
        return new ResponseEntity<>(new ResponseError(response), BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseError> handleMethodArgumentNotValidException(final MethodArgumentNotValidException ex) {
        log.error(DEFAULT_ERROR_HANDLING_PATTERN, ex.getClass().getSimpleName(), ex.getMessage(), ex.getStackTrace());

        var response = ex.getBindingResult().getAllErrors().stream().map(error -> {
            if (error instanceof FieldError err) {
                return new BusinessResponseError(null, err.getField() + " " + err.getDefaultMessage(), MDC.get(REQUEST_ID_HEADER));
            } else {
                return new BusinessResponseError(null, error.getDefaultMessage(), MDC.get(REQUEST_ID_HEADER));
            }
        }).collect(Collectors.toSet());

        log.error("Total violation errors: {} being: {}",
                response.size(),
                response.stream().map(BusinessResponseError::message).collect(Collectors.joining(", "))
        );

        return new ResponseEntity<>(new ResponseError(response), BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ResponseError> handleConstraintViolationException(final ConstraintViolationException ex) {
        log.error(DEFAULT_ERROR_HANDLING_PATTERN, ex.getClass().getSimpleName(), ex.getMessage(), ex.getStackTrace());

        var response = ex.getConstraintViolations()
                .stream()
                .map(error -> new BusinessResponseError(null, error.getMessage(), MDC.get(REQUEST_ID_HEADER)))
                .collect(Collectors.toSet());

        log.error("Total violation errors: {} being: {}",
                response.size(),
                response.stream().map(BusinessResponseError::message).collect(Collectors.joining(", "))
        );

        return new ResponseEntity<>(new ResponseError(response), BAD_REQUEST);
    }

}
