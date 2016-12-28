package com.example.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCodes {

    SYSTEM_EXCEPTION("001", "System exception"),
    INTERNAL_SERVER_ERROR("002", "Internal server error"),
    BACKEND_PROFILE_EXCEPTION("003", "Backend system down"),
    AUTHENTICATION_EXCEPTION("004", "Not Authorized check the corr id");
    
    private final String errorCode;
    private final String errorMessage;

}