package com.example.demoswagger.controller;

import com.example.demoswagger.module.ResourceException;
import com.example.demoswagger.module.ResourceResponse;
import com.example.demoswagger.response.Response;
import com.example.demoswagger.response.ResponseDto;
import com.example.demoswagger.sqlserver.BodyParameterFirst;
import com.example.demoswagger.sqlserver.DateFromToDto;
import com.example.demoswagger.sqlserver.commodity.Commodity;
import com.example.demoswagger.sqlserver.commodity.CommodityServiceImpl;
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

@Api(tags = "Hàng hóa", description = "Hàng hóa API")
@RestController
public class CommodityController {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CommodityServiceImpl serviceImpl;

    public CommodityController(CommodityServiceImpl serviceImpl) {
        super();
        this.serviceImpl = serviceImpl;
    }

    @ApiOperation(value = "Danh sách hàng hóa bán")
    @PostMapping("/Commodity/Sell")
    public ResponseEntity<ResponseDto> getSellCommodity(@RequestBody @Valid DateFromToDto param) {
        ResponseDto ResponseDto = modelMapper.map(Response.class, ResponseDto.class);
        try {
            List<Commodity> listResponse = serviceImpl
                    .getListSellCommod(modelMapper.map(param, new BodyParameterFirst(
                            param.getDateFrom(),
                            param.getDateTo()).getClass()));
            if (listResponse.isEmpty()) {
                throw new ResourceException("List " + HttpStatus.NOT_FOUND.getReasonPhrase());
            }
            List<Object> listObject = new ArrayList<Object>();
            for (Commodity response : listResponse) {
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

    @ApiOperation(value = "Danh sách hàng hóa mua")
    @PostMapping("/Commodity/Buy")
    public ResponseEntity<ResponseDto> getBuyCommodity(@RequestBody @Valid DateFromToDto param) {
        ResponseDto ResponseDto = modelMapper.map(Response.class, ResponseDto.class);
        try {
            List<Commodity> listResponse = serviceImpl
                    .getListBuyCommod(modelMapper.map(param, new BodyParameterFirst(
                            param.getDateFrom(),
                            param.getDateTo()).getClass()));
            if (listResponse.isEmpty()) {
                throw new ResourceException("List " + HttpStatus.NOT_FOUND.getReasonPhrase());
            }
            List<Object> listObject = new ArrayList<Object>();
            for (Commodity response : listResponse) {
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

    @ApiOperation(value = "Danh sách hàng hóa nhập trả")
    @PostMapping("/Commodity/Import")
    public ResponseEntity<ResponseDto> getImportCommodity(@RequestBody @Valid DateFromToDto param) {
        ResponseDto ResponseDto = modelMapper.map(Response.class, ResponseDto.class);
        try {
            List<Commodity> listResponse = serviceImpl
                    .getListImportCommod(modelMapper.map(param, new BodyParameterFirst(
                            param.getDateFrom(),
                            param.getDateTo()).getClass()));
            if (listResponse.isEmpty()) {
                throw new ResourceException("List " + HttpStatus.NOT_FOUND.getReasonPhrase());
            }
            List<Object> listObject = new ArrayList<Object>();
            for (Commodity response : listResponse) {
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

    @ApiOperation(value = "Danh sách hàng hóa xuất trả")
    @PostMapping("/Commodity/Export")
    public ResponseEntity<ResponseDto> getExportCommodity(@RequestBody @Valid DateFromToDto param) {
        ResponseDto ResponseDto = modelMapper.map(Response.class, ResponseDto.class);
        try {
            List<Commodity> listResponse = serviceImpl
                    .getListExportCommod(modelMapper.map(param, new BodyParameterFirst(
                            param.getDateFrom(),
                            param.getDateTo()).getClass()));
            if (listResponse.isEmpty()) {
                throw new ResourceException("List " + HttpStatus.NOT_FOUND.getReasonPhrase());
            }
            List<Object> listObject = new ArrayList<Object>();
            for (Commodity response : listResponse) {
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

    @ApiOperation(value = "Danh sách hàng hóa bán theo khách hàng")
    @PostMapping("/Commodity/Client/Sell")
    public ResponseEntity<ResponseDto> getSellClient(@RequestBody @Valid DateFromToDto param) {
        ResponseDto ResponseDto = modelMapper.map(Response.class, ResponseDto.class);
        try {
            List<Commodity> listResponse = serviceImpl
                    .getListSellClient(modelMapper.map(param, new BodyParameterFirst(
                            param.getDateFrom(),
                            param.getDateTo()).getClass()));
            if (listResponse.isEmpty()) {
                throw new ResourceException("List " + HttpStatus.NOT_FOUND.getReasonPhrase());
            }
            List<Object> listObject = new ArrayList<Object>();
            for (Commodity response : listResponse) {
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

    @ApiOperation(value = "Danh sách hàng hóa nhập trả theo khách hàng")
    @PostMapping("/Commodity/Client/Import")
    public ResponseEntity<ResponseDto> getImportClient(@RequestBody @Valid DateFromToDto param) {
        ResponseDto ResponseDto = modelMapper.map(Response.class, ResponseDto.class);
        try {
            List<Commodity> listResponse = serviceImpl
                    .getListImportClient(modelMapper.map(param, new BodyParameterFirst(
                            param.getDateFrom(),
                            param.getDateTo()).getClass()));
            if (listResponse.isEmpty()) {
                throw new ResourceException("List " + HttpStatus.NOT_FOUND.getReasonPhrase());
            }
            List<Object> listObject = new ArrayList<Object>();
            for (Commodity response : listResponse) {
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

    @ApiOperation(value = "Danh sách hàng hóa bán theo nhà cung cấp")
    @PostMapping("/Commodity/Supplier/Buy")
    public ResponseEntity<ResponseDto> getBuySupplier(@RequestBody @Valid DateFromToDto param) {
        ResponseDto ResponseDto = modelMapper.map(Response.class, ResponseDto.class);
        try {
            List<Commodity> listResponse = serviceImpl
                    .getListBuySupplier(modelMapper.map(param, new BodyParameterFirst(
                            param.getDateFrom(),
                            param.getDateTo()).getClass()));
            if (listResponse.isEmpty()) {
                throw new ResourceException("List " + HttpStatus.NOT_FOUND.getReasonPhrase());
            }
            List<Object> listObject = new ArrayList<Object>();
            for (Commodity response : listResponse) {
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

    @ApiOperation(value = "Danh sách hàng hóa xuất trả theo nhà cung cấp")
    @PostMapping("/Commodity/Supplier/Export")
    public ResponseEntity<ResponseDto> getExportSupplier(@RequestBody @Valid DateFromToDto param) {
        ResponseDto ResponseDto = modelMapper.map(Response.class, ResponseDto.class);
        try {
            List<Commodity> listResponse = serviceImpl
                    .getListExportSupplier(modelMapper.map(param, new BodyParameterFirst(
                            param.getDateFrom(),
                            param.getDateTo()).getClass()));
            if (listResponse.isEmpty()) {
                throw new ResourceException("List " + HttpStatus.NOT_FOUND.getReasonPhrase());
            }
            List<Object> listObject = new ArrayList<Object>();
            for (Commodity response : listResponse) {
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

    @ApiOperation(value = "Danh sách hàng hóa bán theo quầy")
    @PostMapping("/Commodity/Counter/Sell")
    public ResponseEntity<ResponseDto> getSellCounter(@RequestBody @Valid DateFromToDto param) {
        ResponseDto ResponseDto = modelMapper.map(Response.class, ResponseDto.class);
        try {
            List<Commodity> listResponse = serviceImpl
                    .getListSellCounter(modelMapper.map(param, new BodyParameterFirst(
                            param.getDateFrom(),
                            param.getDateTo()).getClass()));
            if (listResponse.isEmpty()) {
                throw new ResourceException("List " + HttpStatus.NOT_FOUND.getReasonPhrase());
            }
            List<Object> listObject = new ArrayList<Object>();
            for (Commodity response : listResponse) {
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
