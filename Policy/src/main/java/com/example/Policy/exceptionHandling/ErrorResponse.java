package com.example.Policy.exceptionHandling;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ErrorResponse {

    private LocalDateTime timestamp;
    private String description;
    private String path;

    public ErrorResponse(LocalDateTime now, String message, String description) {
    }
}
