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
import com.example.demoswagger.SQLServer.*;
import com.example.demoswagger.SQLServer.Debt.*;

@RestController
public class DebtController {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private DebtServiceImpl debtServiceImpl;

    public DebtController(DebtServiceImpl debtServiceImpl) {
        super();
        this.debtServiceImpl = debtServiceImpl;
    }

    @PostMapping("/Debt/Collect")
    public ResponseEntity<ResponseDto> getCollectDebt(@RequestBody @Valid DateToDto param) {
        ResponseDto ResponseDto = modelMapper.map(Response.class, ResponseDto.class);
        try {
            List<Debt> listResponse = debtServiceImpl
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

    @PostMapping("/Debt/Pay")
    public ResponseEntity<ResponseDto> getPayDebt(@RequestBody @Valid DateToDto param) {
        ResponseDto ResponseDto = modelMapper.map(Response.class, ResponseDto.class);
        try {
            List<Debt> listResponse = debtServiceImpl
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

    @PostMapping("/DebtChart/Collect")
    public ResponseEntity<ResponseDto> getCollectChart(@RequestBody @Valid DateToDto param) {
        ResponseDto ResponseDto = modelMapper.map(Response.class, ResponseDto.class);
        try {
            List<DebtChart> listResponse = debtServiceImpl
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

    @PostMapping("/DebtChart/Collect/Detail")
    public ResponseEntity<ResponseDto> getCollectChartDetail(@RequestBody @Valid DateFromToCodeRestDto param) {
        ResponseDto ResponseDto = modelMapper.map(Response.class, ResponseDto.class);
        try {
            List<DebtChartDetail> listResponse = debtServiceImpl
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

    @PostMapping("/DebtChartWithDetail/Collect")
    public ResponseEntity<ResponseDto> getCollectChartWithDetail(@RequestBody @Valid DateToDto param) {
        ResponseDto ResponseDto = modelMapper.map(Response.class, ResponseDto.class);
        try {
            List<DebtChart> listResponse = debtServiceImpl
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

    @PostMapping("/DebtChart/Pay")
    public ResponseEntity<ResponseDto> getPayChart(@RequestBody @Valid DateToDto param) {
        ResponseDto ResponseDto = modelMapper.map(Response.class, ResponseDto.class);
        try {
            List<DebtChart> listResponse = debtServiceImpl
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

    @PostMapping("/DebtChart/Pay/Detail")
    public ResponseEntity<ResponseDto> getPayChartDetail(@RequestBody @Valid DateFromToCodeRestDto param) {
        ResponseDto ResponseDto = modelMapper.map(Response.class, ResponseDto.class);
        try {
            List<DebtChartDetail> listResponse = debtServiceImpl
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

    @PostMapping("/DebtChartWithDetail/Pay")
    public ResponseEntity<ResponseDto> getPayChartWithDetail(@RequestBody @Valid DateToDto param) {
        ResponseDto ResponseDto = modelMapper.map(Response.class, ResponseDto.class);
        try {
            List<DebtChart> listResponse = debtServiceImpl
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
    @PostMapping("/DebtMapClient/Negative")
    public ResponseEntity<ResponseDto> getMapClientNegative(@RequestBody @Valid DateToTypeDto param) {
        ResponseDto ResponseDto = modelMapper.map(Response.class, ResponseDto.class);
        try {
            List<DebtMap> listResponse = debtServiceImpl
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

    @PostMapping("/DebtMapClient/Negative/Detail")
    public ResponseEntity<ResponseDto> getMapClientNegativeDetail(@RequestBody @Valid DateToTypeValueDto param) {
        ResponseDto ResponseDto = modelMapper.map(Response.class, ResponseDto.class);
        try {
            List<DebtMapDetail> listResponse = debtServiceImpl
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

    @PostMapping("/DebtMapClientWithDetail/Negative")
    public ResponseEntity<ResponseDto> getMapClientNegativeWithDetail(@RequestBody @Valid DateToTypeDto param) {
        ResponseDto ResponseDto = modelMapper.map(Response.class, ResponseDto.class);
        try {
            List<DebtMap> listResponse = debtServiceImpl
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
