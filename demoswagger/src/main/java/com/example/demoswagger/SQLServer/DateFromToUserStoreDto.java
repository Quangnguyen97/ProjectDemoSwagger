package com.example.demoswagger.SQLServer;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "Tham số cho hàng hóa KHO LẺ", required = true)
public class DateFromToUserStoreDto {
    @ApiModelProperty(notes = "Từ ngày", example = "2020/01/01")
    private String dateFrom;

    @ApiModelProperty(notes = "Đến ngày", example = "2022/01/01")
    private String dateTo;

    @ApiModelProperty(notes = "Người dùng", example = "admin")
    private String user;

    @ApiModelProperty(notes = "Mã kho", example = "CODE01")
    private String store;
}
