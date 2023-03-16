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
            List<Commodity> listResponse = commodityServiceImpl
                    .getListSellCommod(modelMapper.map(dateFromDateToDto, DateFromDateTo.class));
            if (listResponse.isEmpty()) {
                throw new ResourceException("List sell commodity " + HttpStatus.NOT_FOUND.getReasonPhrase());
            }
            List<Object> listObject = new ArrayList<Object>();
            for (Commodity commodity : listResponse) {
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
            List<Commodity> listResponse = commodityServiceImpl
                    .getListBuyCommod(modelMapper.map(dateFromDateToDto, DateFromDateTo.class));
            if (listResponse.isEmpty()) {
                throw new ResourceException("List buy commodity " + HttpStatus.NOT_FOUND.getReasonPhrase());
            }
            List<Object> listObject = new ArrayList<Object>();
            for (Commodity commodity : listResponse) {
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
            List<Commodity> listResponse = commodityServiceImpl
                    .getListImportCommod(modelMapper.map(dateFromDateToDto, DateFromDateTo.class));
            if (listResponse.isEmpty()) {
                throw new ResourceException("List import commodity " + HttpStatus.NOT_FOUND.getReasonPhrase());
            }
            List<Object> listObject = new ArrayList<Object>();
            for (Commodity commodity : listResponse) {
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
            List<Commodity> listResponse = commodityServiceImpl
                    .getListExportCommod(modelMapper.map(dateFromDateToDto, DateFromDateTo.class));
            if (listResponse.isEmpty()) {
                throw new ResourceException("List export commodity " + HttpStatus.NOT_FOUND.getReasonPhrase());
            }
            List<Object> listObject = new ArrayList<Object>();
            for (Commodity commodity : listResponse) {
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

    @PostMapping("/Commodity/Client/Sell")
    public ResponseEntity<ResponseDto> getSellClient(@RequestBody @Valid DateFromDateToDto dateFromDateToDto) {
        ResponseDto ResponseDto = modelMapper.map(Response.class, ResponseDto.class);
        try {
            List<Commodity> listResponse = commodityServiceImpl
                    .getListSellClient(modelMapper.map(dateFromDateToDto, DateFromDateTo.class));
            if (listResponse.isEmpty()) {
                throw new ResourceException("List commodity sell by client " + HttpStatus.NOT_FOUND.getReasonPhrase());
            }
            List<Object> listObject = new ArrayList<Object>();
            for (Commodity commodity : listResponse) {
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

    @PostMapping("/Commodity/Client/Import")
    public ResponseEntity<ResponseDto> getImportClient(@RequestBody @Valid DateFromDateToDto dateFromDateToDto) {
        ResponseDto ResponseDto = modelMapper.map(Response.class, ResponseDto.class);
        try {
            List<Commodity> listResponse = commodityServiceImpl
                    .getListImportClient(modelMapper.map(dateFromDateToDto, DateFromDateTo.class));
            if (listResponse.isEmpty()) {
                throw new ResourceException(
                        "List commodity import by client " + HttpStatus.NOT_FOUND.getReasonPhrase());
            }
            List<Object> listObject = new ArrayList<Object>();
            for (Commodity commodity : listResponse) {
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

    @PostMapping("/Commodity/Supplier/Buy")
    public ResponseEntity<ResponseDto> getBuySupplier(@RequestBody @Valid DateFromDateToDto dateFromDateToDto) {
        ResponseDto ResponseDto = modelMapper.map(Response.class, ResponseDto.class);
        try {
            List<Commodity> listResponse = commodityServiceImpl
                    .getListBuySupplier(modelMapper.map(dateFromDateToDto, DateFromDateTo.class));
            if (listResponse.isEmpty()) {
                throw new ResourceException(
                        "List commodity buy by supplier " + HttpStatus.NOT_FOUND.getReasonPhrase());
            }
            List<Object> listObject = new ArrayList<Object>();
            for (Commodity commodity : listResponse) {
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

    @PostMapping("/Commodity/Supplier/Export")
    public ResponseEntity<ResponseDto> getExportSupplier(@RequestBody @Valid DateFromDateToDto dateFromDateToDto) {
        ResponseDto ResponseDto = modelMapper.map(Response.class, ResponseDto.class);
        try {
            List<Commodity> listResponse = commodityServiceImpl
                    .getListExportSupplier(modelMapper.map(dateFromDateToDto, DateFromDateTo.class));
            if (listResponse.isEmpty()) {
                throw new ResourceException(
                        "List commodity export by supplier " + HttpStatus.NOT_FOUND.getReasonPhrase());
            }
            List<Object> listObject = new ArrayList<Object>();
            for (Commodity commodity : listResponse) {
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

    @PostMapping("/Commodity/Counter/Sell")
    public ResponseEntity<ResponseDto> getSellCounter(@RequestBody @Valid DateFromDateToDto dateFromDateToDto) {
        ResponseDto ResponseDto = modelMapper.map(Response.class, ResponseDto.class);
        try {
            List<Commodity> listResponse = commodityServiceImpl
                    .getListSellCounter(modelMapper.map(dateFromDateToDto, DateFromDateTo.class));
            if (listResponse.isEmpty()) {
                throw new ResourceException(
                        "List commodity sell by counter " + HttpStatus.NOT_FOUND.getReasonPhrase());
            }
            List<Object> listObject = new ArrayList<Object>();
            for (Commodity commodity : listResponse) {
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
