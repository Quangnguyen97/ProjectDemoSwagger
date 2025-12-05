package com.example.demoswagger.controller;

import com.example.demoswagger.module.ResourceException;
import com.example.demoswagger.module.ResourceResponse;
import com.example.demoswagger.response.Response;
import com.example.demoswagger.response.ResponseDto;
import com.example.demoswagger.sqlserver.BodyParameterFirst;
import com.example.demoswagger.sqlserver.DateFromToDto;
import com.example.demoswagger.sqlserver.commodity.Commodity;
import com.example.demoswagger.sqlserver.commodity.CommodityServiceImpl;
import io.swagger.annotations.ApiOperation;
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
@Tag(name = "Hàng hóa", description = "Hàng hóa API")
public class CommodityController extends BaseController {

    private final CommodityServiceImpl serviceImpl;

    public CommodityController(ModelMapper modelMapper, CommodityServiceImpl serviceImpl) {
        super(modelMapper);
        this.serviceImpl = serviceImpl;
    }

    @ApiOperation(value = "Danh sách hàng hóa bán")
    @PostMapping("/Commodity/Sell")
    public ResponseEntity<ResponseDto> getSellCommodity(@RequestBody @Valid DateFromToDto param) {
        ResponseDto responseDto = modelMapper.map(Response.class, ResponseDto.class);
        try {
            List<Commodity> listResponse = serviceImpl
                    .getListSellCommod(modelMapper.map(param, BodyParameterFirst.class));
            if (listResponse.isEmpty()) {
                throw new ResourceException("List " + HttpStatus.NOT_FOUND.getReasonPhrase());
            }
            List<Object> listObject = new ArrayList<>(listResponse);
            ResourceResponse.responseDto(responseDto, HttpStatus.OK.value(),
                    HttpStatus.OK.getReasonPhrase(), "", listObject);
            return ResponseEntity.status(HttpStatus.OK).body(responseDto);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(handleExpectationFailed(e.getMessage()));
        }
    }

    @ApiOperation(value = "Danh sách hàng hóa mua")
    @PostMapping("/Commodity/Buy")
    public ResponseEntity<ResponseDto> getBuyCommodity(@RequestBody @Valid DateFromToDto param) {
        ResponseDto responseDto = modelMapper.map(Response.class, ResponseDto.class);
        try {
            List<Commodity> listResponse = serviceImpl
                    .getListBuyCommod(modelMapper.map(param, BodyParameterFirst.class));
            if (listResponse.isEmpty()) {
                throw new ResourceException("List " + HttpStatus.NOT_FOUND.getReasonPhrase());
            }
            List<Object> listObject = new ArrayList<>(listResponse);
            ResourceResponse.responseDto(responseDto, HttpStatus.OK.value(),
                    HttpStatus.OK.getReasonPhrase(), "", listObject);
            return ResponseEntity.status(HttpStatus.OK).body(responseDto);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(handleExpectationFailed(e.getMessage()));
        }
    }

    @ApiOperation(value = "Danh sách hàng hóa nhập trả")
    @PostMapping("/Commodity/Import")
    public ResponseEntity<ResponseDto> getImportCommodity(@RequestBody @Valid DateFromToDto param) {
        ResponseDto responseDto = modelMapper.map(Response.class, ResponseDto.class);
        try {
            List<Commodity> listResponse = serviceImpl
                    .getListImportCommod(modelMapper.map(param, BodyParameterFirst.class));
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

    @ApiOperation(value = "Danh sách hàng hóa xuất trả")
    @PostMapping("/Commodity/Export")
    public ResponseEntity<ResponseDto> getExportCommodity(@RequestBody @Valid DateFromToDto param) {
        ResponseDto ResponseDto = modelMapper.map(Response.class, ResponseDto.class);
        try {
            List<Commodity> listResponse = serviceImpl
                    .getListExportCommod(modelMapper.map(param, BodyParameterFirst.class));
            if (listResponse.isEmpty()) {
                throw new ResourceException("List " + HttpStatus.NOT_FOUND.getReasonPhrase());
            }
            List<Object> listObject = new ArrayList<>(listResponse);
            ResponseDto = ResourceResponse.responseDto(ResponseDto, HttpStatus.OK.value(),
                    HttpStatus.OK.getReasonPhrase(), "", listObject);
            return ResponseEntity.status(HttpStatus.OK).body(ResponseDto);
        } catch (Exception e) {
            ResponseDto = ResourceResponse.responseDto(ResponseDto, HttpStatus.EXPECTATION_FAILED.value(),
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
                    .getListSellClient(modelMapper.map(param, BodyParameterFirst.class));
            if (listResponse.isEmpty()) {
                throw new ResourceException("List " + HttpStatus.NOT_FOUND.getReasonPhrase());
            }
            List<Object> listObject = new ArrayList<>(listResponse);
            ResponseDto = ResourceResponse.responseDto(ResponseDto, HttpStatus.OK.value(),
                    HttpStatus.OK.getReasonPhrase(), "", listObject);
            return ResponseEntity.status(HttpStatus.OK).body(ResponseDto);
        } catch (Exception e) {
            ResponseDto = ResourceResponse.responseDto(ResponseDto, HttpStatus.EXPECTATION_FAILED.value(),
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
                    .getListImportClient(modelMapper.map(param, BodyParameterFirst.class));
            if (listResponse.isEmpty()) {
                throw new ResourceException("List " + HttpStatus.NOT_FOUND.getReasonPhrase());
            }
            List<Object> listObject = new ArrayList<>(listResponse);
            ResponseDto = ResourceResponse.responseDto(ResponseDto, HttpStatus.OK.value(),
                    HttpStatus.OK.getReasonPhrase(), "", listObject);
            return ResponseEntity.status(HttpStatus.OK).body(ResponseDto);
        } catch (Exception e) {
            ResponseDto = ResourceResponse.responseDto(ResponseDto, HttpStatus.EXPECTATION_FAILED.value(),
                    HttpStatus.EXPECTATION_FAILED.getReasonPhrase(), e.getMessage(), null);
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(ResponseDto);
        }
    }

    @ApiOperation(value = "Danh sách hàng hóa bán theo nhà cung cấp")
    @PostMapping("/Commodity/Supplier/Buy")
    public ResponseEntity<ResponseDto> getBuySupplier(@RequestBody @Valid DateFromToDto param) {
        ResponseDto responseDto = modelMapper.map(Response.class, ResponseDto.class);
        try {
            List<Commodity> listResponse = serviceImpl
                    .getListBuySupplier(modelMapper.map(param, BodyParameterFirst.class));
            if (listResponse.isEmpty()) {
                throw new ResourceException("List " + HttpStatus.NOT_FOUND.getReasonPhrase());
            }
            List<Object> listObject = new ArrayList<>(listResponse);
            ResourceResponse.responseDto(initializeResponseDto(), HttpStatus.OK.value(),
                    HttpStatus.OK.getReasonPhrase(), "", listObject);
            return ResponseEntity.status(HttpStatus.OK).body(responseDto);
        } catch (Exception e) {
            ResourceResponse.responseDto(responseDto, HttpStatus.EXPECTATION_FAILED.value(),
                    HttpStatus.EXPECTATION_FAILED.getReasonPhrase(), e.getMessage(), null);
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(responseDto);
        }
    }

    @ApiOperation(value = "Danh sách hàng hóa xuất trả theo nhà cung cấp")
    @PostMapping("/Commodity/Supplier/Export")
    public ResponseEntity<ResponseDto> getExportSupplier(@RequestBody @Valid DateFromToDto param) {
        ResponseDto responseDto = modelMapper.map(Response.class, ResponseDto.class);
        try {
            List<Commodity> listResponse = serviceImpl
                    .getListExportSupplier(modelMapper.map(param, BodyParameterFirst.class));
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

    @ApiOperation(value = "Danh sách hàng hóa bán theo quầy")
    @PostMapping("/Commodity/Counter/Sell")
    public ResponseEntity<ResponseDto> getSellCounter(@RequestBody @Valid DateFromToDto param) {
        ResponseDto responseDto = modelMapper.map(Response.class, ResponseDto.class);
        try {
            List<Commodity> listResponse = serviceImpl
                    .getListSellCounter(modelMapper.map(param, BodyParameterFirst.class));
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
