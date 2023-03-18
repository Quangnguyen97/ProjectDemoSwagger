package com.example.demoswagger.SQLServer;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DateFromToCodeRestTypeDto {
    @ApiModelProperty(notes = "Từ ngày", example = "2020/01/01")
    private String dateFrom;

    @ApiModelProperty(notes = "Đến ngày", example = "2022/01/01")
    private String dateTo;

    @ApiModelProperty(notes = "Mã đối tượng", example = "CODE01")
    private String code;

    @ApiModelProperty(notes = "Đánh dấu phần còn lại", example = "0")
    private Integer codeRest;

    @ApiModelProperty(notes = "0: danh sách âm | 1: danh sách dương", example = "0")
    private Integer type;
}
