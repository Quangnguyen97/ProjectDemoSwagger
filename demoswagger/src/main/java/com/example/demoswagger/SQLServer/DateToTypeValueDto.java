package com.example.demoswagger.SQLServer;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DateToTypeValueDto {
    @ApiModelProperty(notes = "Đến ngày", example = "2022/01/01")
    private String dateTo;

    @ApiModelProperty(notes = "Tên loại ánh xạ", example = "TYPE01")
    private String codeType;

    @ApiModelProperty(notes = "Mã ánh xạ", example = "CODE01")
    private String codeValue;
}
