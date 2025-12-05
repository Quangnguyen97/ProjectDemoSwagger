package com.example.demoswagger.module;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ResourceException extends RuntimeException {

    private static final String MESSAGE_FORMAT = "%s have exception with %s : '%s";

    public ResourceException() {
        super();
    }

    public ResourceException(String message, Throwable cause) {
        super(message, cause);
    }

    public ResourceException(String message) {
        super(message);
    }

    public ResourceException(Throwable cause) {
        super(cause);
    }

    public ResourceException(String resourceName, String fieldName, Object fieldValue) {
        super(String.format(MESSAGE_FORMAT, resourceName, fieldName, fieldValue));
    }
}
