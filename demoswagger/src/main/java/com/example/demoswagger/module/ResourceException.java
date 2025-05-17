package com.example.demoswagger.module;

import lombok.Getter;
import lombok.Setter;

@Setter
public class ResourceException extends RuntimeException {

    @Getter
    private String resourceName;
    @Getter
    private String fieldName;
    @Getter
    private Object fieldValue;

    private String message;

    public ResourceException() {
        super();
    }

    public ResourceException(String message, Throwable cause) {
        super(message, cause);
        this.message = message;
    }

    public ResourceException(String message) {
        super(message);
        this.message = message;
    }

    public ResourceException(Throwable cause) {
        super(cause);
    }

    public ResourceException(String resourceName, String fieldName, Object fieldValue) {
        super(String.format("%s have exception with %s : '%s'", resourceName, fieldName, fieldValue));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
        this.message = String.format("%s have exception with %s : '%s'", resourceName, fieldName, fieldValue);
    }

    @Override
    public String getMessage() {
        return message;
    }

}
