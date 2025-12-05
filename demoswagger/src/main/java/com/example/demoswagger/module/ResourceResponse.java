package com.example.demoswagger.module;

import com.example.demoswagger.response.ResponseDto;
import lombok.experimental.UtilityClass;

import java.util.List;

@UtilityClass
public class ResourceResponse {

    public static ResponseDto responseDto(ResponseDto responseDto, int status, String description,
                                          String message, List<Object> listObject) {
        try {
            responseDto.setStatus(status);
            responseDto.setDescription(description);
            responseDto.setMessage(message);
            responseDto.setResponse(listObject);
            return responseDto;
        } catch (Exception e) {
            throw new ResourceException(e.getMessage());
        }
    }
}
