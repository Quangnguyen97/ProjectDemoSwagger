package com.example.demoswagger.module;

import com.example.demoswagger.response.ResponseDto;

import java.util.List;

public class ResourceResponse {

    public static ResponseDto ResponseDto(ResponseDto ResponseDto, int status, String description,
                                          String message, List<Object> listObject) {
        try {
            ResponseDto.setStatus(status);
            ResponseDto.setDescription(description);
            ResponseDto.setMessage(message);
            ResponseDto.setResponse(listObject);
            return ResponseDto;
        } catch (Exception e) {
            throw new ResourceException(e.getMessage());
        }
    }
}
