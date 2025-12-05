package com.example.demoswagger.controller;

import com.example.demoswagger.module.ResourceException;
import com.example.demoswagger.module.ResourceResponse;
import com.example.demoswagger.response.Response;
import com.example.demoswagger.response.ResponseDto;
import com.example.demoswagger.sqlserver.BodyParameterFirst;
import com.example.demoswagger.sqlserver.DateFromToDto;
import com.example.demoswagger.sqlserver.cash.CashBook;
import com.example.demoswagger.sqlserver.cash.CashReceiptPayment;
import com.example.demoswagger.sqlserver.cash.CashServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@Tag(name = "Thu chi", description = "Thu chi API")
public class CashController extends BaseController {

    private final CashServiceImpl serviceImpl;

    public CashController(ModelMapper modelMapper, CashServiceImpl serviceImpl) {
        super(modelMapper);
        this.serviceImpl = serviceImpl;
    }

    @Operation(summary = "Danh sách SỔ QUỸ tiền mặt")
    @PostMapping("/Cash/Book")
    public ResponseEntity<ResponseDto> getCashBook(@RequestBody @Valid DateFromToDto param) {
        ResponseDto responseDto = modelMapper.map(Response.class, ResponseDto.class);
        try {
            List<CashBook> listResponse = serviceImpl
                    .getListCashBook(modelMapper.map(param, BodyParameterFirst.class));
            if (listResponse.isEmpty()) {
                throw new ResourceException("List " + HttpStatus.NOT_FOUND.getReasonPhrase());
            }
            List<Object> listObject = new ArrayList<>(listResponse);
            ResourceResponse.responseDto(responseDto, HttpStatus.OK.value(),
                    HttpStatus.OK.getReasonPhrase(), "", listObject);
            return ResponseEntity.status(HttpStatus.OK).body(responseDto);
        } catch (Exception e) {
            ResourceResponse.responseDto(responseDto, HttpStatus.EXPECTATION_FAILED.value(),
                    HttpStatus.EXPECTATION_FAILED.getReasonPhrase(), e.getMessage(), null);
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(responseDto);
        }
    }

    @Operation(summary = "Danh sách THU tiền mặt")
    @PostMapping("/Cash/Receipt")
    public ResponseEntity<ResponseDto> getCashReceipt(@RequestBody @Valid DateFromToDto param) {
        ResponseDto responseDto = modelMapper.map(Response.class, ResponseDto.class);
        try {
            List<CashReceiptPayment> listResponse = serviceImpl
                    .getListCashReceipt(modelMapper.map(param, BodyParameterFirst.class));
            if (listResponse.isEmpty()) {
                throw new ResourceException("List " + HttpStatus.NOT_FOUND.getReasonPhrase());
            }
            List<Object> listObject = new ArrayList<>(listResponse);
            ResourceResponse.responseDto(responseDto, HttpStatus.OK.value(),
                    HttpStatus.OK.getReasonPhrase(), "", listObject);
            return ResponseEntity.status(HttpStatus.OK).body(responseDto);
        } catch (Exception e) {
            ResourceResponse.responseDto(responseDto, HttpStatus.EXPECTATION_FAILED.value(),
                    HttpStatus.EXPECTATION_FAILED.getReasonPhrase(), e.getMessage(), null);
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(responseDto);
        }
    }

    @Operation(summary = "Danh sách CHI tiền mặt")
    @PostMapping("/Cash/Payment")
    public ResponseEntity<ResponseDto> getCashPayment(@RequestBody @Valid DateFromToDto param) {
        ResponseDto responseDto = modelMapper.map(Response.class, ResponseDto.class);
        try {
            List<CashReceiptPayment> listResponse = serviceImpl
                    .getListCashPayment(modelMapper.map(param, BodyParameterFirst.class));
            if (listResponse.isEmpty()) {
                throw new ResourceException("List " + HttpStatus.NOT_FOUND.getReasonPhrase());
            }
            List<Object> listObject = new ArrayList<>(listResponse);
            ResourceResponse.responseDto(responseDto, HttpStatus.OK.value(),
                    HttpStatus.OK.getReasonPhrase(), "", listObject);
            return ResponseEntity.status(HttpStatus.OK).body(responseDto);
        } catch (Exception e) {
            ResourceResponse.responseDto(responseDto, HttpStatus.EXPECTATION_FAILED.value(),
                    HttpStatus.EXPECTATION_FAILED.getReasonPhrase(), e.getMessage(), null);
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(responseDto);
        }
    }
}
