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
import com.example.demoswagger.SQLServer.BodyParameterFirst;
import com.example.demoswagger.SQLServer.DateToDto;
import com.example.demoswagger.SQLServer.Debt.Debt;
import com.example.demoswagger.SQLServer.Debt.DebtServiceImpl;

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
    public ResponseEntity<ResponseDto> getSellCommodity(@RequestBody @Valid DateToDto dateToDto) {
        ResponseDto ResponseDto = modelMapper.map(Response.class, ResponseDto.class);
        try {
            List<Debt> listResponse = debtServiceImpl
                    .getListCollectDebt(modelMapper.map(dateToDto, new BodyParameterFirst(
                            dateToDto.getDateTo()).getClass()));
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
