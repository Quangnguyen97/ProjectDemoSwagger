package com.example.demoswagger.sqlserver;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "Tham số giới hạn thời gian ĐẾN", required = true)
public class DateToDto {
    @ApiModelProperty(notes = "Đến ngày", example = "2022/01/01")
    private String dateTo;
}
