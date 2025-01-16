package com.inditex.interview.infrastructure.controller.rest.error;

import com.inditex.interview.domain.error.InvalidParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {Throwable.class})
    public ResponseEntity<Object> handleThrowable(final Throwable ex, final WebRequest request)
            throws Exception {
        return super.handleException((Exception) ex, request);
    }

    @ExceptionHandler({InvalidParameter.class})
    public ResponseEntity<Object> handleInvalidParameter(Exception ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .body(ex.getMessage());
    }

    @ExceptionHandler({PriceNotFoundException.class})
    public ResponseEntity<Object> handlePriceNotFound(Exception ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.APPLICATION_JSON)
                .body(ex.getMessage());
    }
}
