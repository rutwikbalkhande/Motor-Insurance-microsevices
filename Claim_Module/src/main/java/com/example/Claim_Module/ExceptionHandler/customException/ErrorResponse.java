package com.example.Claim_Module.ExceptionHandler.customException;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ErrorResponse {

    private LocalDateTime timeStamp;
    private String message;
    private String path;
}
