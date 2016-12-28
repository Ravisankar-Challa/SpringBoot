package com.example.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class ApplicationException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    private final String errorCode;

    public ApplicationException(final String errorCode, final String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public ApplicationException(final String errorCode, final String message, final Throwable rootCause) {
        super(message, rootCause);
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }
}
