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
public class HangHoaController {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CommodityServiceImpl hangHoaServiceImpl;

    public HangHoaController(CommodityServiceImpl hangHoaServiceImpl) {
        super();
        this.hangHoaServiceImpl = hangHoaServiceImpl;
    }

    @PostMapping("/HangHoa/BanHang")
    public ResponseEntity<ResponseDto> getSellCommodity(@RequestBody @Valid DateFromDateToDto dateFromDateToDto) {
        ResponseDto ResponseDto = modelMapper.map(Response.class, ResponseDto.class);
        try {
            List<Commodity> listBanHangHangHoa = hangHoaServiceImpl
                    .getListSellCommod(modelMapper.map(dateFromDateToDto, DateFromDateTo.class));
            if (listBanHangHangHoa.isEmpty()) {
                throw new ResourceException("List " + HttpStatus.NOT_FOUND.getReasonPhrase());
            }
            List<Object> listObject = new ArrayList<Object>();
            for (Commodity hangHoa : listBanHangHangHoa) {
                listObject.add(hangHoa);
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

    @PostMapping("/HangHoa/MuaHang")
    public ResponseEntity<ResponseDto> getBuyCommodity(@RequestBody @Valid DateFromDateToDto dateFromDateToDto) {
        ResponseDto ResponseDto = modelMapper.map(Response.class, ResponseDto.class);
        try {
            List<Commodity> listBanHangHangHoa = hangHoaServiceImpl
                    .getListBuyCommod(modelMapper.map(dateFromDateToDto, DateFromDateTo.class));
            if (listBanHangHangHoa.isEmpty()) {
                throw new ResourceException("List " +
                        HttpStatus.NOT_FOUND.getReasonPhrase());
            }
            List<Object> listObject = new ArrayList<Object>();
            for (Commodity hangHoa : listBanHangHangHoa) {
                listObject.add(hangHoa);
            }
            ResponseDto = ResourceResponse.ResponseDto(ResponseDto,
                    HttpStatus.OK.value(),
                    HttpStatus.OK.getReasonPhrase(), "", listObject);
            return ResponseEntity.status(HttpStatus.OK).body(ResponseDto);
        } catch (Exception e) {
            ResponseDto = ResourceResponse.ResponseDto(ResponseDto,
                    HttpStatus.EXPECTATION_FAILED.value(),
                    HttpStatus.EXPECTATION_FAILED.getReasonPhrase(), e.getMessage(), null);
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(ResponseDto);
        }
    }

    @PostMapping("/HangHoa/NhapTra")
    public ResponseEntity<ResponseDto> getImportCommodity(@RequestBody @Valid DateFromDateToDto dateFromDateToDto) {
        ResponseDto ResponseDto = modelMapper.map(Response.class, ResponseDto.class);
        try {
            List<Commodity> listBanHangHangHoa = hangHoaServiceImpl
                    .getListImportCommod(modelMapper.map(dateFromDateToDto, DateFromDateTo.class));
            if (listBanHangHangHoa.isEmpty()) {
                throw new ResourceException("List " +
                        HttpStatus.NOT_FOUND.getReasonPhrase());
            }
            List<Object> listObject = new ArrayList<Object>();
            for (Commodity hangHoa : listBanHangHangHoa) {
                listObject.add(hangHoa);
            }
            ResponseDto = ResourceResponse.ResponseDto(ResponseDto,
                    HttpStatus.OK.value(),
                    HttpStatus.OK.getReasonPhrase(), "", listObject);
            return ResponseEntity.status(HttpStatus.OK).body(ResponseDto);
        } catch (Exception e) {
            ResponseDto = ResourceResponse.ResponseDto(ResponseDto,
                    HttpStatus.EXPECTATION_FAILED.value(),
                    HttpStatus.EXPECTATION_FAILED.getReasonPhrase(), e.getMessage(), null);
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(ResponseDto);
        }
    }

    @PostMapping("/HangHoa/XuatTra")
    public ResponseEntity<ResponseDto> getExportCommodity(@RequestBody @Valid DateFromDateToDto dateFromDateToDto) {
        ResponseDto ResponseDto = modelMapper.map(Response.class, ResponseDto.class);
        try {
            List<Commodity> listBanHangHangHoa = hangHoaServiceImpl
                    .getListExportCommod(modelMapper.map(dateFromDateToDto, DateFromDateTo.class));
            if (listBanHangHangHoa.isEmpty()) {
                throw new ResourceException("List " +
                        HttpStatus.NOT_FOUND.getReasonPhrase());
            }
            List<Object> listObject = new ArrayList<Object>();
            for (Commodity hangHoa : listBanHangHangHoa) {
                listObject.add(hangHoa);
            }
            ResponseDto = ResourceResponse.ResponseDto(ResponseDto,
                    HttpStatus.OK.value(),
                    HttpStatus.OK.getReasonPhrase(), "", listObject);
            return ResponseEntity.status(HttpStatus.OK).body(ResponseDto);
        } catch (Exception e) {
            ResponseDto = ResourceResponse.ResponseDto(ResponseDto,
                    HttpStatus.EXPECTATION_FAILED.value(),
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
