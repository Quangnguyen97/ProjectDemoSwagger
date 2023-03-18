package com.example.demoswagger.SQLServer;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "Tham số cho DOANH SỐ theo mã ánh xạ chi tiết", required = true)
public class DateFromToTypeValueDto {
    @ApiModelProperty(notes = "Từ ngày", example = "2020/01/01")
    private String dateFrom;

    @ApiModelProperty(notes = "Đến ngày", example = "2022/01/01")
    private String dateTo;

    @ApiModelProperty(notes = "Tên loại ánh xạ", example = "TYPE01")
    private String codeType;

    @ApiModelProperty(notes = "Mã ánh xạ" + "<br>"
            + "Có thể nhiều hơn 1, VD: CODE01, CODE02, ...", example = "CODE01")
    private String codeValue;
}
