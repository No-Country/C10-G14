package com.C10G14.WorldFitBackend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class SqlConstraintException extends ResponseStatusException {
    public SqlConstraintException(String message) {
        super(HttpStatus.CONFLICT, message);
    }
}
