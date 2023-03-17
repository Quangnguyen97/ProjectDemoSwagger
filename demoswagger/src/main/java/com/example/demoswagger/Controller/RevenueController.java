package com.example.demoswagger.Controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import com.example.demoswagger.Module.*;
import com.example.demoswagger.Response.*;
import com.example.demoswagger.SQLServer.*;
import com.example.demoswagger.SQLServer.Revenue.*;

@RestController
@Api(tags = "Doanh số", description = "Doanh số API")
public class RevenueController {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private RevenueServiceImpl serviceImpl;

    public RevenueController(RevenueServiceImpl serviceImpl) {
        super();
        this.serviceImpl = serviceImpl;
    }

    // Top chart
    @ApiOperation(value = "Danh sách doanh số KHÁCH HÀNG ÂM theo biểu đồ")
    @PostMapping("/RevenueChartClient/Negative")
    public ResponseEntity<ResponseDto> getChartClientNegative(@RequestBody @Valid DateToDto param) {
        ResponseDto ResponseDto = modelMapper.map(Response.class, ResponseDto.class);
        try {
            List<RevenueChart> listResponse = serviceImpl
                    .getListChartClientNegative(modelMapper.map(param, new BodyParameterFirst(
                            param.getDateTo()).getClass()));
            if (listResponse.isEmpty()) {
                throw new ResourceException(
                        "List revenue chart client negative " + HttpStatus.NOT_FOUND.getReasonPhrase());
            }
            List<Object> listObject = new ArrayList<Object>();
            for (RevenueChart response : listResponse) {
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
}
