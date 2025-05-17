package com.example.demoswagger.controller;

import com.example.demoswagger.module.ResourceException;
import com.example.demoswagger.module.ResourceResponse;
import com.example.demoswagger.response.Response;
import com.example.demoswagger.response.ResponseDto;
import com.example.demoswagger.sqlserver.BodyParameterSecond;
import com.example.demoswagger.sqlserver.CodeTypeDto;
import com.example.demoswagger.sqlserver.codemap.CodeMap;
import com.example.demoswagger.sqlserver.codemap.CodeMapDetail;
import com.example.demoswagger.sqlserver.codemap.CodeMapServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@Api(tags = "Ánh xạ", description = "Ánh xạ API")
public class CodeMapController {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CodeMapServiceImpl serviceImpl;

    public CodeMapController(CodeMapServiceImpl serviceImpl) {
        super();
        this.serviceImpl = serviceImpl;
    }

    @ApiOperation(value = "Danh sách ÁNH XẠ")
    @GetMapping("/CodeMap")
    public ResponseEntity<ResponseDto> getCodeMap() {
        ResponseDto ResponseDto = modelMapper.map(Response.class, ResponseDto.class);
        try {
            List<CodeMap> listResponse = serviceImpl.getListCodeMap();
            if (listResponse.isEmpty()) {
                throw new ResourceException("List " + HttpStatus.NOT_FOUND.getReasonPhrase());
            }
            List<Object> listObject = new ArrayList<Object>();
            for (CodeMap response : listResponse) {
                listObject.add(response);
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

    @ApiOperation(value = "Danh sách ÁNH XẠ chi tiết")
    @PostMapping("/CodeMap/Detail")
    public ResponseEntity<ResponseDto> getCodeMapDetail(@RequestBody @Valid CodeTypeDto param) {
        ResponseDto ResponseDto = modelMapper.map(Response.class, ResponseDto.class);
        try {
            List<CodeMapDetail> listResponse = serviceImpl
                    .getListCodeMapDetail(modelMapper.map(param, new BodyParameterSecond(
                            param.getCodeType()).getClass()));
            if (listResponse.isEmpty()) {
                throw new ResourceException("List " + HttpStatus.NOT_FOUND.getReasonPhrase());
            }
            List<Object> listObject = new ArrayList<Object>();
            for (CodeMapDetail response : listResponse) {
                listObject.add(response);
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

    @ApiOperation(value = "Danh sách ÁNH XẠ bao gồm chi tiết")
    @GetMapping("/CodeMapWithDetail")
    public ResponseEntity<ResponseDto> getCodeMapWithDetail() {
        ResponseDto ResponseDto = modelMapper.map(Response.class, ResponseDto.class);
        try {
            List<CodeMap> listResponse = serviceImpl.getListCodeMapWithDetail();
            if (listResponse.isEmpty()) {
                throw new ResourceException("List " + HttpStatus.NOT_FOUND.getReasonPhrase());
            }
            List<Object> listObject = new ArrayList<Object>();
            for (CodeMap response : listResponse) {
                listObject.add(response);
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
