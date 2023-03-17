package com.example.demoswagger.SQLServer;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DateToDto {
    @ApiModelProperty(notes = "Đến ngày", example = "2022/01/01")
    private String dateTo;
}
