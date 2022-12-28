package com.example.imageUI.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ImageNotFoundRestException extends ResponseStatusException {
    public ImageNotFoundRestException(String message) {
        super(HttpStatus.CONFLICT, message);
    }
}
