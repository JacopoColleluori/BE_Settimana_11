package com.epicode.project.week_11.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CatalogueExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CatalogueException.class)
    protected ResponseEntity<Object> handleEpicodeException(CatalogueException ex) {

        ApiError apiError = new ApiError(ex.getMessage(), HttpStatus.NOT_FOUND);

        return buildResponseEntity(apiError);
    }

    private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }
}
