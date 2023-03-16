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

import com.example.demoswagger.Module.*;
import com.example.demoswagger.Response.*;
import com.example.demoswagger.SQLServer.DateFromDateTo;
import com.example.demoswagger.SQLServer.DateFromDateToDto;
import com.example.demoswagger.SQLServer.Commodity.Commodity;
import com.example.demoswagger.SQLServer.Commodity.CommodityServiceImpl;

@RestController
public class CommodityController {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CommodityServiceImpl commodityServiceImpl;

    public CommodityController(CommodityServiceImpl commodityServiceImpl) {
        super();
        this.commodityServiceImpl = commodityServiceImpl;
    }

    @PostMapping("/Commodity/Sell")
    public ResponseEntity<ResponseDto> getSellCommodity(@RequestBody @Valid DateFromDateToDto dateFromDateToDto) {
        ResponseDto ResponseDto = modelMapper.map(Response.class, ResponseDto.class);
        try {
            List<Commodity> listSellCommod = commodityServiceImpl
                    .getListSellCommod(modelMapper.map(dateFromDateToDto, DateFromDateTo.class));
            if (listSellCommod.isEmpty()) {
                throw new ResourceException("List sell commodity " + HttpStatus.NOT_FOUND.getReasonPhrase());
            }
            List<Object> listObject = new ArrayList<Object>();
            for (Commodity commodity : listSellCommod) {
                listObject.add(commodity);
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

    @PostMapping("/Commodity/Buy")
    public ResponseEntity<ResponseDto> getBuyCommodity(@RequestBody @Valid DateFromDateToDto dateFromDateToDto) {
        ResponseDto ResponseDto = modelMapper.map(Response.class, ResponseDto.class);
        try {
            List<Commodity> listBuyCommod = commodityServiceImpl
                    .getListBuyCommod(modelMapper.map(dateFromDateToDto, DateFromDateTo.class));
            if (listBuyCommod.isEmpty()) {
                throw new ResourceException("List buy commodity " + HttpStatus.NOT_FOUND.getReasonPhrase());
            }
            List<Object> listObject = new ArrayList<Object>();
            for (Commodity commodity : listBuyCommod) {
                listObject.add(commodity);
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

    @PostMapping("/Commodity/Import")
    public ResponseEntity<ResponseDto> getImportCommodity(@RequestBody @Valid DateFromDateToDto dateFromDateToDto) {
        ResponseDto ResponseDto = modelMapper.map(Response.class, ResponseDto.class);
        try {
            List<Commodity> listImportCommod = commodityServiceImpl
                    .getListImportCommod(modelMapper.map(dateFromDateToDto, DateFromDateTo.class));
            if (listImportCommod.isEmpty()) {
                throw new ResourceException("List import commodity " + HttpStatus.NOT_FOUND.getReasonPhrase());
            }
            List<Object> listObject = new ArrayList<Object>();
            for (Commodity commodity : listImportCommod) {
                listObject.add(commodity);
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

    @PostMapping("/Commodity/Export")
    public ResponseEntity<ResponseDto> getExportCommodity(@RequestBody @Valid DateFromDateToDto dateFromDateToDto) {
        ResponseDto ResponseDto = modelMapper.map(Response.class, ResponseDto.class);
        try {
            List<Commodity> listExportCommod = commodityServiceImpl
                    .getListExportCommod(modelMapper.map(dateFromDateToDto, DateFromDateTo.class));
            if (listExportCommod.isEmpty()) {
                throw new ResourceException("List export commodity " + HttpStatus.NOT_FOUND.getReasonPhrase());
            }
            List<Object> listObject = new ArrayList<Object>();
            for (Commodity commodity : listExportCommod) {
                listObject.add(commodity);
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
