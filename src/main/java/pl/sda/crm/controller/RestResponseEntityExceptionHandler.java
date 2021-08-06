package pl.sda.crm.controller;

import lombok.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import pl.sda.crm.exception.BusinessServiceException;

@ControllerAdvice
final class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @Value
    static class ErrorMessage {
        String message;
        int code;
    }

    @ExceptionHandler(BusinessServiceException.class)
    public final ResponseEntity<Object> handleException(BusinessServiceException ex) {
        return ResponseEntity.badRequest().body(new ErrorMessage(ex.getMessage(), 400));
    }
}
