package com.example.demoswagger.controller;

import com.example.demoswagger.module.ResourceException;
import com.example.demoswagger.module.ResourceResponse;
import com.example.demoswagger.response.ResponseDto;
import com.example.demoswagger.sqlserver.BodyParameterSecond;
import com.example.demoswagger.sqlserver.CodeTypeDto;
import com.example.demoswagger.sqlserver.codemap.CodeMap;
import com.example.demoswagger.sqlserver.codemap.CodeMapDetail;
import com.example.demoswagger.sqlserver.codemap.CodeMapServiceImpl;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@Tag(name = "Ánh xạ", description = "Ánh xạ API")
public class CodeMapController extends BaseController {

    private final CodeMapServiceImpl serviceImpl;

    public CodeMapController(ModelMapper modelMapper, CodeMapServiceImpl serviceImpl) {
        super(modelMapper);
        this.serviceImpl = serviceImpl;
    }

    @ApiOperation(value = "Danh sách ÁNH XẠ")
    @GetMapping("/CodeMap")
    public ResponseEntity<ResponseDto> getCodeMap() {
        ResponseDto responseDto = new ResponseDto();
        try {
            List<CodeMap> listResponse = serviceImpl.getListCodeMap();
            if (listResponse.isEmpty()) {
                throw new ResourceException("List " + HttpStatus.NOT_FOUND.getReasonPhrase());
            }
            ResourceResponse.responseDto(responseDto, HttpStatus.OK.value(),
                    HttpStatus.OK.getReasonPhrase(), "", List.copyOf(listResponse));
            return ResponseEntity.status(HttpStatus.OK).body(responseDto);
        } catch (ResourceException e) {
            ResourceResponse.responseDto(responseDto, HttpStatus.EXPECTATION_FAILED.value(),
                    HttpStatus.EXPECTATION_FAILED.getReasonPhrase(), e.getMessage(), null);
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(responseDto);
        }
    }

    @ApiOperation(value = "Danh sách ÁNH XẠ chi tiết")
    @PostMapping("/CodeMap/Detail")
    public ResponseEntity<ResponseDto> getCodeMapDetail(@RequestBody @Valid CodeTypeDto param) {
        ResponseDto responseDto = new ResponseDto();
        try {
            List<CodeMapDetail> listResponse = serviceImpl
                    .getListCodeMapDetail(modelMapper.map(param, BodyParameterSecond.class));
            if (listResponse.isEmpty()) {
                throw new ResourceException("List " + HttpStatus.NOT_FOUND.getReasonPhrase());
            }
            ResourceResponse.responseDto(responseDto, HttpStatus.OK.value(),
                    HttpStatus.OK.getReasonPhrase(), "", List.copyOf(listResponse));
            return ResponseEntity.status(HttpStatus.OK).body(responseDto);
        } catch (ResourceException e) {
            ResourceResponse.responseDto(responseDto, HttpStatus.EXPECTATION_FAILED.value(),
                    HttpStatus.EXPECTATION_FAILED.getReasonPhrase(), e.getMessage(), null);
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(responseDto);
        }
    }

    @ApiOperation(value = "Danh sách ÁNH XẠ bao gồm chi tiết")
    @GetMapping("/CodeMapWithDetail")
    public ResponseEntity<ResponseDto> getCodeMapWithDetail() {
        ResponseDto responseDto = new ResponseDto();
        try {
            List<CodeMap> listResponse = serviceImpl.getListCodeMapWithDetail();
            if (listResponse.isEmpty()) {
                throw new ResourceException("List " + HttpStatus.NOT_FOUND.getReasonPhrase());
            }
            ResourceResponse.responseDto(responseDto, HttpStatus.OK.value(),
                    HttpStatus.OK.getReasonPhrase(), "", List.copyOf(listResponse));
            return ResponseEntity.status(HttpStatus.OK).body(responseDto);
        } catch (ResourceException e) {
            ResourceResponse.responseDto(responseDto, HttpStatus.EXPECTATION_FAILED.value(),
                    HttpStatus.EXPECTATION_FAILED.getReasonPhrase(), e.getMessage(), null);
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(responseDto);
        }
    }
}
