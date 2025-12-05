package com.example.demoswagger.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Response {
    private int status;
    private String description;
    private String message;
    @JsonProperty(value = "response")
    private List<Object> resp;

    public Response(int status, String description, String message, List<Object> response) {
        super();
        this.status = status;
        this.description = description;
        this.message = message;
        this.resp = response;
    }
}
