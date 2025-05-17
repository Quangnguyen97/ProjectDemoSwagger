package com.example.demoswagger.sqlserver;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "Tham số cho CÔNG NỢ theo biểu đồ chi tiết", required = true)
public class DateFromToCodeRestDto {
    @ApiModelProperty(notes = "Từ ngày", example = "2020/01/01")
    private String dateFrom;

    @ApiModelProperty(notes = "Đến ngày", example = "2022/01/01")
    private String dateTo;

    @ApiModelProperty(notes = "Mã đối tượng, VD: CODE01" + "<br>"
            + "Nếu codeRest = 1 thì nhập vào là tất cả mã đã hiển thị, VD: CODE01, CODE02, ...", example = "CODE01")
    private String code;

    @ApiModelProperty(notes = "Đánh dấu phần còn lại" + "<br>"
            + "Nếu = 1 thì code nhập vào là tất cả mã đã hiển thị, VD: CODE01, CODE02, ...", example = "0")
    private Integer codeRest;
}
