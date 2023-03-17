package com.example.demoswagger.SQLServer;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DateFromToDto {
    @ApiModelProperty(notes = "Từ ngày", example = "2020/01/01")
    private String dateFrom;

    @ApiModelProperty(notes = "Đến ngày", example = "2022/01/01")
    private String dateTo;
}
