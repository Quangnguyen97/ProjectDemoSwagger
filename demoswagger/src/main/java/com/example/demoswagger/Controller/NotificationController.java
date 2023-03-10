package com.example.demoswagger.Controller;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demoswagger.Notification.*;
import com.example.demoswagger.Module.*;
import com.example.demoswagger.Response.*;

@RestController
public class NotificationController {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private NotificationServiceImpl notificationServiceImpl;

    public NotificationController(NotificationServiceImpl notificationServiceImpl) {
        super();
        this.notificationServiceImpl = notificationServiceImpl;
    }

    @GetMapping("/notification/promotion")
    public ResponseEntity<ResponseDto> pushPromotion() {
        ResponseDto ResponseDto = modelMapper.map(Response.class, ResponseDto.class);
        try {
            List<String> listToken = notificationServiceImpl.pushPromotion();
            if (listToken.isEmpty()) {
                throw new ResourceException("List token " + HttpStatus.NOT_FOUND.getReasonPhrase());
            }
            List<Object> listObject = new ArrayList<Object>();
            for (String Token : listToken) {
                listObject.add(Token);
            }
            ResponseDto = ResourceResponse.ResponseDto(ResponseDto, HttpStatus.OK.value(),
                    HttpStatus.OK.getReasonPhrase(), "", listObject);
            return ResponseEntity.status(HttpStatus.OK).body(ResponseDto);
        } catch (Exception e) {
            ResponseDto = ResourceResponse.ResponseDto(ResponseDto, HttpStatus.EXPECTATION_FAILED.value(),
                    HttpStatus.EXPECTATION_FAILED.getReasonPhrase(), e.getMessage(), null);
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(ResponseDto);
        }
    }

    @GetMapping("/notification/all")
    public ResponseEntity<ResponseDto> pushAll() {
        ResponseDto ResponseDto = modelMapper.map(Response.class, ResponseDto.class);
        try {
            List<Notification> listNotification = notificationServiceImpl.pushAll();
            if (listNotification.isEmpty()) {
                throw new ResourceException("List notification " + HttpStatus.NOT_FOUND.getReasonPhrase());
            }
            List<Object> listObject = new ArrayList<Object>();
            for (Notification notification : listNotification) {
                listObject.add(notification);
            }
            ResponseDto = ResourceResponse.ResponseDto(ResponseDto, HttpStatus.OK.value(),
                    HttpStatus.OK.getReasonPhrase(), "", listObject);
            return ResponseEntity.status(HttpStatus.OK).body(ResponseDto);
        } catch (Exception e) {
            ResponseDto = ResourceResponse.ResponseDto(ResponseDto, HttpStatus.EXPECTATION_FAILED.value(),
                    HttpStatus.EXPECTATION_FAILED.getReasonPhrase(), e.getMessage(), null);
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(ResponseDto);
        }
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    private ResponseEntity<ResponseDto> HandleHttpMessageException(
            HttpMessageNotReadableException exception) {
        ResponseDto ResponseDto = modelMapper.map(Response.class, ResponseDto.class);
        try {
            ResponseDto = ResourceResponse.ResponseDto(ResponseDto, HttpStatus.BAD_REQUEST.value(),
                    HttpStatus.BAD_REQUEST.getReasonPhrase(), exception.getMessage(), null);
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(ResponseDto);
        } catch (Exception e) {
            ResponseDto = ResourceResponse.ResponseDto(ResponseDto, HttpStatus.EXPECTATION_FAILED.value(),
                    HttpStatus.EXPECTATION_FAILED.getReasonPhrase(), e.getMessage(), null);
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(ResponseDto);
        }
    }
}
