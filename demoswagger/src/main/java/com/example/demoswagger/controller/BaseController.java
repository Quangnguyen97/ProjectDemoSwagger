package com.example.demoswagger.controller;

import com.example.demoswagger.module.ResourceResponse;
import com.example.demoswagger.response.Response;
import com.example.demoswagger.response.ResponseDto;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

public abstract class BaseController {

    protected final ModelMapper modelMapper;

    protected BaseController(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    protected ResponseDto initializeResponseDto() {
        return modelMapper.map(Response.class, ResponseDto.class);
    }

    protected ResponseDto handleSuccess(List<Object> listObject) {
        return ResourceResponse.responseDto(initializeResponseDto(), HttpStatus.OK.value(),
                HttpStatus.OK.getReasonPhrase(), "Success", listObject);
    }

    protected ResponseDto handleExpectationFailed(String message) {
        return ResourceResponse.responseDto(initializeResponseDto(), HttpStatus.EXPECTATION_FAILED.value(),
                HttpStatus.EXPECTATION_FAILED.getReasonPhrase(), message, null);
    }

    protected ResponseEntity<ResponseDto> handleException(Exception e, ResponseDto responseDto) {
        ResourceResponse.responseDto(responseDto,
                HttpStatus.EXPECTATION_FAILED.value(),
                HttpStatus.EXPECTATION_FAILED.getReasonPhrase(),
                e.getMessage(), null);
        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(responseDto);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    protected ResponseEntity<ResponseDto> handleHttpMessageException(
            HttpMessageNotReadableException exception) {
        ResponseDto responseDto = initializeResponseDto();
        try {
            ResourceResponse.responseDto(responseDto,
                    HttpStatus.BAD_REQUEST.value(),
                    HttpStatus.BAD_REQUEST.getReasonPhrase(),
                    exception.getMessage(), null);
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(responseDto);
        } catch (Exception e) {
            return handleException(e, responseDto);
        }
    }
}
