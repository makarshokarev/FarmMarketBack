package com.example.farmmarketback.exception;

public class ErrorResponse {
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    private String message;

    public ErrorResponse(String message) {
        this.message = message;
    }
}
