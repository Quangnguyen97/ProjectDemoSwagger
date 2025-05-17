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
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@Api(tags = "Thu chi", description = "Thu chi API")
public class CashController {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CashServiceImpl serviceImpl;

    public CashController(CashServiceImpl serviceImpl) {
        super();
        this.serviceImpl = serviceImpl;
    }

    @ApiOperation(value = "Danh sách SỔ QUỸ tiền mặt")
    @PostMapping("/Cash/Book")
    public ResponseEntity<ResponseDto> getCashBook(@RequestBody @Valid DateFromToDto param) {
        ResponseDto ResponseDto = modelMapper.map(Response.class, ResponseDto.class);
        try {
            List<CashBook> listResponse = serviceImpl
                    .getListCashBook(modelMapper.map(param, new BodyParameterFirst(
                            param.getDateFrom(),
                            param.getDateTo()).getClass()));
            if (listResponse.isEmpty()) {
                throw new ResourceException("List " + HttpStatus.NOT_FOUND.getReasonPhrase());
            }
            List<Object> listObject = new ArrayList<Object>();
            for (CashBook response : listResponse) {
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

    @ApiOperation(value = "Danh sách THU tiền mặt")
    @PostMapping("/Cash/Receipt")
    public ResponseEntity<ResponseDto> getCashReceipt(@RequestBody @Valid DateFromToDto param) {
        ResponseDto ResponseDto = modelMapper.map(Response.class, ResponseDto.class);
        try {
            List<CashReceiptPayment> listResponse = serviceImpl
                    .getListCashReceipt(modelMapper.map(param, new BodyParameterFirst(
                            param.getDateFrom(),
                            param.getDateTo()).getClass()));
            if (listResponse.isEmpty()) {
                throw new ResourceException("List " + HttpStatus.NOT_FOUND.getReasonPhrase());
            }
            List<Object> listObject = new ArrayList<Object>();
            for (CashReceiptPayment response : listResponse) {
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

    @ApiOperation(value = "Danh sách CHI tiền mặt")
    @PostMapping("/Cash/Payment")
    public ResponseEntity<ResponseDto> getCashPayment(@RequestBody @Valid DateFromToDto param) {
        ResponseDto ResponseDto = modelMapper.map(Response.class, ResponseDto.class);
        try {
            List<CashReceiptPayment> listResponse = serviceImpl
                    .getListCashPayment(modelMapper.map(param, new BodyParameterFirst(
                            param.getDateFrom(),
                            param.getDateTo()).getClass()));
            if (listResponse.isEmpty()) {
                throw new ResourceException("List " + HttpStatus.NOT_FOUND.getReasonPhrase());
            }
            List<Object> listObject = new ArrayList<Object>();
            for (CashReceiptPayment response : listResponse) {
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
