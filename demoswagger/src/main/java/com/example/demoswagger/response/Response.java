package com.example.demoswagger.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Response {
    private int status;
    private String description;
    private String message;
    private List<Object> response;

    public Response(int status, String description, String message, List<Object> response) {
        super();
        this.status = status;
        this.description = description;
        this.message = message;
        this.response = response;
    }
}
