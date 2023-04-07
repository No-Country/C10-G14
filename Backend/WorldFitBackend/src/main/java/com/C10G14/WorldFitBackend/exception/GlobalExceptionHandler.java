package com.C10G14.WorldFitBackend.exception;

import com.C10G14.WorldFitBackend.dto.MessageResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<MessageResponse> handleResponseStatusException(ResponseStatusException ex) {
        HttpStatusCode status = ex.getStatusCode();
        MessageResponse response = new MessageResponse(ex.getStatusCode().value(),ex.getReason());
        return new ResponseEntity<>(response, status);
    }

}
