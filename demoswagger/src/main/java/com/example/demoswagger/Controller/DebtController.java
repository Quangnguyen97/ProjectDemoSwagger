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
import com.example.demoswagger.SQLServer.Debt.*;

@RestController
@Api(tags = "Công nợ", description = "Công nợ API")
public class DebtController {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private DebtServiceImpl serviceImpl;

    public DebtController(DebtServiceImpl serviceImpl) {
        super();
        this.serviceImpl = serviceImpl;
    }

    @ApiOperation(value = "Danh sách công nợ phải THU")
    @PostMapping("/Debt/Collect")
    public ResponseEntity<ResponseDto> getCollectDebt(@RequestBody @Valid DateToDto param) {
        ResponseDto ResponseDto = modelMapper.map(Response.class, ResponseDto.class);
        try {
            List<Debt> listResponse = serviceImpl
                    .getListCollectDebt(modelMapper.map(param, new BodyParameterFirst(
                            param.getDateTo()).getClass()));
            if (listResponse.isEmpty()) {
                throw new ResourceException("List collect debt " + HttpStatus.NOT_FOUND.getReasonPhrase());
            }
            List<Object> listObject = new ArrayList<Object>();
            for (Debt response : listResponse) {
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

    @ApiOperation(value = "Danh sách công nợ phải TRẢ")
    @PostMapping("/Debt/Pay")
    public ResponseEntity<ResponseDto> getPayDebt(@RequestBody @Valid DateToDto param) {
        ResponseDto ResponseDto = modelMapper.map(Response.class, ResponseDto.class);
        try {
            List<Debt> listResponse = serviceImpl
                    .getListPayDebt(modelMapper.map(param, new BodyParameterFirst(
                            param.getDateTo()).getClass()));
            if (listResponse.isEmpty()) {
                throw new ResourceException("List pay debt " + HttpStatus.NOT_FOUND.getReasonPhrase());
            }
            List<Object> listObject = new ArrayList<Object>();
            for (Debt response : listResponse) {
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

    // Top chart
    @ApiOperation(value = "Danh sách công nợ phải THU theo biểu đồ")
    @PostMapping("/DebtChart/Collect")
    public ResponseEntity<ResponseDto> getCollectChart(@RequestBody @Valid DateToDto param) {
        ResponseDto ResponseDto = modelMapper.map(Response.class, ResponseDto.class);
        try {
            List<DebtChart> listResponse = serviceImpl
                    .getListCollectChart(modelMapper.map(param, new BodyParameterFirst(
                            param.getDateTo()).getClass()));
            if (listResponse.isEmpty()) {
                throw new ResourceException("List collect debt chart " + HttpStatus.NOT_FOUND.getReasonPhrase());
            }
            List<Object> listObject = new ArrayList<Object>();
            for (DebtChart response : listResponse) {
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

    @ApiOperation(value = "Danh sách công nợ phải THU theo biểu đồ chi tiết")
    @PostMapping("/DebtChart/Collect/Detail")
    public ResponseEntity<ResponseDto> getCollectChartDetail(@RequestBody @Valid DateFromToCodeRestDto param) {
        ResponseDto ResponseDto = modelMapper.map(Response.class, ResponseDto.class);
        try {
            List<DebtChartDetail> listResponse = serviceImpl
                    .getListCollectChartDetail(modelMapper.map(param, new BodyParameterFirst(
                            param.getDateFrom(),
                            param.getDateTo(),
                            param.getCode(),
                            param.getCodeRest()).getClass()));
            if (listResponse.isEmpty()) {
                throw new ResourceException("List collect debt chart detail " + HttpStatus.NOT_FOUND.getReasonPhrase());
            }
            List<Object> listObject = new ArrayList<Object>();
            for (DebtChartDetail response : listResponse) {
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

    @ApiOperation(value = "Danh sách công nợ phải THU bao gồm chi tiết theo biểu đồ")
    @PostMapping("/DebtChartWithDetail/Collect")
    public ResponseEntity<ResponseDto> getCollectChartWithDetail(@RequestBody @Valid DateToDto param) {
        ResponseDto ResponseDto = modelMapper.map(Response.class, ResponseDto.class);
        try {
            List<DebtChart> listResponse = serviceImpl
                    .getListCollectChartWithDetail(modelMapper.map(param, new BodyParameterFirst(
                            param.getDateTo()).getClass()));
            if (listResponse.isEmpty()) {
                throw new ResourceException(
                        "List collect debt chart with detail " + HttpStatus.NOT_FOUND.getReasonPhrase());
            }
            List<Object> listObject = new ArrayList<Object>();
            for (DebtChart response : listResponse) {
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

    @ApiOperation(value = "Danh sách công nợ phải TRẢ theo biểu đồ")
    @PostMapping("/DebtChart/Pay")
    public ResponseEntity<ResponseDto> getPayChart(@RequestBody @Valid DateToDto param) {
        ResponseDto ResponseDto = modelMapper.map(Response.class, ResponseDto.class);
        try {
            List<DebtChart> listResponse = serviceImpl
                    .getListPayChart(modelMapper.map(param, new BodyParameterFirst(
                            param.getDateTo()).getClass()));
            if (listResponse.isEmpty()) {
                throw new ResourceException("List pay debt chart " + HttpStatus.NOT_FOUND.getReasonPhrase());
            }
            List<Object> listObject = new ArrayList<Object>();
            for (DebtChart response : listResponse) {
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

    @ApiOperation(value = "Danh sách công nợ phải TRẢ theo biểu đồ chi tiết")
    @PostMapping("/DebtChart/Pay/Detail")
    public ResponseEntity<ResponseDto> getPayChartDetail(@RequestBody @Valid DateFromToCodeRestDto param) {
        ResponseDto ResponseDto = modelMapper.map(Response.class, ResponseDto.class);
        try {
            List<DebtChartDetail> listResponse = serviceImpl
                    .getListPayChartDetail(modelMapper.map(param, new BodyParameterFirst(
                            param.getDateFrom(),
                            param.getDateTo(),
                            param.getCode(),
                            param.getCodeRest()).getClass()));
            if (listResponse.isEmpty()) {
                throw new ResourceException("List pay debt chart detail " + HttpStatus.NOT_FOUND.getReasonPhrase());
            }
            List<Object> listObject = new ArrayList<Object>();
            for (DebtChartDetail response : listResponse) {
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

    @ApiOperation(value = "Danh sách công nợ phải TRẢ bao gồm chi tiết theo biểu đồ")
    @PostMapping("/DebtChartWithDetail/Pay")
    public ResponseEntity<ResponseDto> getPayChartWithDetail(@RequestBody @Valid DateToDto param) {
        ResponseDto ResponseDto = modelMapper.map(Response.class, ResponseDto.class);
        try {
            List<DebtChart> listResponse = serviceImpl
                    .getListPayChartWithDetail(modelMapper.map(param, new BodyParameterFirst(
                            param.getDateTo()).getClass()));
            if (listResponse.isEmpty()) {
                throw new ResourceException(
                        "List pay debt chart with detail " + HttpStatus.NOT_FOUND.getReasonPhrase());
            }
            List<Object> listObject = new ArrayList<Object>();
            for (DebtChart response : listResponse) {
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

    // Code map
    @ApiOperation(value = "Danh sách công nợ KHÁCH HÀNG ÂM theo mã ánh xạ")
    @PostMapping("/DebtMapClient/Negative")
    public ResponseEntity<ResponseDto> getMapClientNegative(@RequestBody @Valid DateToTypeDto param) {
        ResponseDto ResponseDto = modelMapper.map(Response.class, ResponseDto.class);
        try {
            List<DebtMap> listResponse = serviceImpl
                    .getListMapClientNegative(modelMapper.map(param, new BodyParameterSecond(
                            param.getDateTo(),
                            param.getCodeType()).getClass()));
            if (listResponse.isEmpty()) {
                throw new ResourceException("List debt map client negative " + HttpStatus.NOT_FOUND.getReasonPhrase());
            }
            List<Object> listObject = new ArrayList<Object>();
            for (DebtMap response : listResponse) {
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

    @ApiOperation(value = "Danh sách công nợ KHÁCH HÀNG ÂM theo mã ánh xạ chi tiết")
    @PostMapping("/DebtMapClient/Negative/Detail")
    public ResponseEntity<ResponseDto> getMapClientNegativeDetail(@RequestBody @Valid DateToTypeValueDto param) {
        ResponseDto ResponseDto = modelMapper.map(Response.class, ResponseDto.class);
        try {
            List<DebtMapDetail> listResponse = serviceImpl
                    .getListMapClientNegativeDetail(modelMapper.map(param, new BodyParameterSecond(
                            param.getDateTo(),
                            param.getCodeType(),
                            param.getCodeValue()).getClass()));
            if (listResponse.isEmpty()) {
                throw new ResourceException(
                        "List debt map client negative detail " + HttpStatus.NOT_FOUND.getReasonPhrase());
            }
            List<Object> listObject = new ArrayList<Object>();
            for (DebtMapDetail response : listResponse) {
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

    @ApiOperation(value = "Danh sách công nợ KHÁCH HÀNG ÂM bao gồm chi tiết theo mã ánh xạ")
    @PostMapping("/DebtMapClientWithDetail/Negative")
    public ResponseEntity<ResponseDto> getMapClientNegativeWithDetail(@RequestBody @Valid DateToTypeDto param) {
        ResponseDto ResponseDto = modelMapper.map(Response.class, ResponseDto.class);
        try {
            List<DebtMap> listResponse = serviceImpl
                    .getListMapClientNegativeWithDetail(modelMapper.map(param, new BodyParameterSecond(
                            param.getDateTo(),
                            param.getCodeType()).getClass()));
            if (listResponse.isEmpty()) {
                throw new ResourceException(
                        "List debt map client negative with detail " + HttpStatus.NOT_FOUND.getReasonPhrase());
            }
            List<Object> listObject = new ArrayList<Object>();
            for (DebtMap response : listResponse) {
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

    @ApiOperation(value = "Danh sách công nợ KHÁCH HÀNG DƯƠNG theo mã ánh xạ")
    @PostMapping("/DebtMapClient/Positive")
    public ResponseEntity<ResponseDto> getMapClientPositive(@RequestBody @Valid DateToTypeDto param) {
        ResponseDto ResponseDto = modelMapper.map(Response.class, ResponseDto.class);
        try {
            List<DebtMap> listResponse = serviceImpl
                    .getListMapClientPositive(modelMapper.map(param, new BodyParameterSecond(
                            param.getDateTo(),
                            param.getCodeType()).getClass()));
            if (listResponse.isEmpty()) {
                throw new ResourceException("List debt map client positive " + HttpStatus.NOT_FOUND.getReasonPhrase());
            }
            List<Object> listObject = new ArrayList<Object>();
            for (DebtMap response : listResponse) {
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

    @ApiOperation(value = "Danh sách công nợ KHÁCH HÀNG DƯƠNG theo mã ánh xạ chi tiết")
    @PostMapping("/DebtMapClient/Positive/Detail")
    public ResponseEntity<ResponseDto> getMapClientPositiveDetail(@RequestBody @Valid DateToTypeValueDto param) {
        ResponseDto ResponseDto = modelMapper.map(Response.class, ResponseDto.class);
        try {
            List<DebtMapDetail> listResponse = serviceImpl
                    .getListMapClientPositiveDetail(modelMapper.map(param, new BodyParameterSecond(
                            param.getDateTo(),
                            param.getCodeType(),
                            param.getCodeValue()).getClass()));
            if (listResponse.isEmpty()) {
                throw new ResourceException(
                        "List debt map client positive detail " + HttpStatus.NOT_FOUND.getReasonPhrase());
            }
            List<Object> listObject = new ArrayList<Object>();
            for (DebtMapDetail response : listResponse) {
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

    @ApiOperation(value = "Danh sách công nợ KHÁCH HÀNG DƯƠNG bao gồm chi tiết theo mã ánh xạ")
    @PostMapping("/DebtMapClientWithDetail/Positive")
    public ResponseEntity<ResponseDto> getMapClientPositiveWithDetail(@RequestBody @Valid DateToTypeDto param) {
        ResponseDto ResponseDto = modelMapper.map(Response.class, ResponseDto.class);
        try {
            List<DebtMap> listResponse = serviceImpl
                    .getListMapClientPositiveWithDetail(modelMapper.map(param, new BodyParameterSecond(
                            param.getDateTo(),
                            param.getCodeType()).getClass()));
            if (listResponse.isEmpty()) {
                throw new ResourceException(
                        "List debt map client positive with detail " + HttpStatus.NOT_FOUND.getReasonPhrase());
            }
            List<Object> listObject = new ArrayList<Object>();
            for (DebtMap response : listResponse) {
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

    @ApiOperation(value = "Danh sách công nợ NHÀ CUNG CẤP ÂM theo mã ánh xạ")
    @PostMapping("/DebtMapSupplier/Negative")
    public ResponseEntity<ResponseDto> getMapSupplierNegative(@RequestBody @Valid DateToTypeDto param) {
        ResponseDto ResponseDto = modelMapper.map(Response.class, ResponseDto.class);
        try {
            List<DebtMap> listResponse = serviceImpl
                    .getListMapSupplierNegative(modelMapper.map(param, new BodyParameterSecond(
                            param.getDateTo(),
                            param.getCodeType()).getClass()));
            if (listResponse.isEmpty()) {
                throw new ResourceException(
                        "List debt map supplier negative " + HttpStatus.NOT_FOUND.getReasonPhrase());
            }
            List<Object> listObject = new ArrayList<Object>();
            for (DebtMap response : listResponse) {
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

    @ApiOperation(value = "Danh sách công nợ NHÀ CUNG CẤP ÂM theo mã ánh xạ chi tiết")
    @PostMapping("/DebtMapSupplier/Negative/Detail")
    public ResponseEntity<ResponseDto> getMapSupplierNegativeDetail(@RequestBody @Valid DateToTypeValueDto param) {
        ResponseDto ResponseDto = modelMapper.map(Response.class, ResponseDto.class);
        try {
            List<DebtMapDetail> listResponse = serviceImpl
                    .getListMapSupplierNegativeDetail(modelMapper.map(param, new BodyParameterSecond(
                            param.getDateTo(),
                            param.getCodeType(),
                            param.getCodeValue()).getClass()));
            if (listResponse.isEmpty()) {
                throw new ResourceException(
                        "List debt map supplier negative detail " + HttpStatus.NOT_FOUND.getReasonPhrase());
            }
            List<Object> listObject = new ArrayList<Object>();
            for (DebtMapDetail response : listResponse) {
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

    @ApiOperation(value = "Danh sách công nợ NHÀ CUNG CẤP ÂM bao gồm chi tiết theo mã ánh xạ")
    @PostMapping("/DebtMapSupplierWithDetail/Negative")
    public ResponseEntity<ResponseDto> getMapSupplierNegativeWithDetail(@RequestBody @Valid DateToTypeDto param) {
        ResponseDto ResponseDto = modelMapper.map(Response.class, ResponseDto.class);
        try {
            List<DebtMap> listResponse = serviceImpl
                    .getListMapSupplierNegativeWithDetail(modelMapper.map(param, new BodyParameterSecond(
                            param.getDateTo(),
                            param.getCodeType()).getClass()));
            if (listResponse.isEmpty()) {
                throw new ResourceException(
                        "List debt map supplier negative with detail " + HttpStatus.NOT_FOUND.getReasonPhrase());
            }
            List<Object> listObject = new ArrayList<Object>();
            for (DebtMap response : listResponse) {
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

    @ApiOperation(value = "Danh sách công nợ NHÀ CUNG CẤP DƯƠNG theo mã ánh xạ")
    @PostMapping("/DebtMapSupplier/Positive")
    public ResponseEntity<ResponseDto> getMapSupplierPositive(@RequestBody @Valid DateToTypeDto param) {
        ResponseDto ResponseDto = modelMapper.map(Response.class, ResponseDto.class);
        try {
            List<DebtMap> listResponse = serviceImpl
                    .getListMapSupplierPositive(modelMapper.map(param, new BodyParameterSecond(
                            param.getDateTo(),
                            param.getCodeType()).getClass()));
            if (listResponse.isEmpty()) {
                throw new ResourceException(
                        "List debt map supplier positive " + HttpStatus.NOT_FOUND.getReasonPhrase());
            }
            List<Object> listObject = new ArrayList<Object>();
            for (DebtMap response : listResponse) {
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

    @ApiOperation(value = "Danh sách công nợ NHÀ CUNG CẤP DƯƠNG theo mã ánh xạ chi tiết")
    @PostMapping("/DebtMapSupplier/Positive/Detail")
    public ResponseEntity<ResponseDto> getMapSupplierPositiveDetail(@RequestBody @Valid DateToTypeValueDto param) {
        ResponseDto ResponseDto = modelMapper.map(Response.class, ResponseDto.class);
        try {
            List<DebtMapDetail> listResponse = serviceImpl
                    .getListMapSupplierPositiveDetail(modelMapper.map(param, new BodyParameterSecond(
                            param.getDateTo(),
                            param.getCodeType(),
                            param.getCodeValue()).getClass()));
            if (listResponse.isEmpty()) {
                throw new ResourceException(
                        "List debt map supplier positive detail " + HttpStatus.NOT_FOUND.getReasonPhrase());
            }
            List<Object> listObject = new ArrayList<Object>();
            for (DebtMapDetail response : listResponse) {
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

    @ApiOperation(value = "Danh sách công nợ NHÀ CUNG CẤP DƯƠNG bao gồm chi tiết theo mã ánh xạ")
    @PostMapping("/DebtMapSupplierWithDetail/Positive")
    public ResponseEntity<ResponseDto> getMapSupplierPositiveWithDetail(@RequestBody @Valid DateToTypeDto param) {
        ResponseDto ResponseDto = modelMapper.map(Response.class, ResponseDto.class);
        try {
            List<DebtMap> listResponse = serviceImpl
                    .getListMapSupplierPositiveWithDetail(modelMapper.map(param, new BodyParameterSecond(
                            param.getDateTo(),
                            param.getCodeType()).getClass()));
            if (listResponse.isEmpty()) {
                throw new ResourceException(
                        "List debt map supplier positive with detail " + HttpStatus.NOT_FOUND.getReasonPhrase());
            }
            List<Object> listObject = new ArrayList<Object>();
            for (DebtMap response : listResponse) {
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
