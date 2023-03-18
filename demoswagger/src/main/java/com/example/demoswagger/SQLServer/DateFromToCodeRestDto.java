package com.example.demoswagger.SQLServer;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DateFromToCodeRestDto {
        @ApiModelProperty(notes = "Từ ngày", example = "2020/01/01")
        private String dateFrom;

        @ApiModelProperty(notes = "Đến ngày", example = "2022/01/01")
        private String dateTo;

        @ApiModelProperty(notes = "Mã đối tượng, VD: CODE01" + "<br>"
                        + "Nếu codeRest = 1 thì giá trị nhập vào là tất cả mã đã hiển thị, VD: CODE01, CODE01, ...", example = "CODE01")
        private String code;

        @ApiModelProperty(notes = "Đánh dấu phần còn lại" + "<br>"
                        + "Nếu = 1 thì mã đối tượng nhập vào là tất cả mã đã hiển thị", example = "0")
        private Integer codeRest;
}
