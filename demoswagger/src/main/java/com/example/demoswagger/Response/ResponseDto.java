package com.example.demoswagger.Response;

import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class ResponseDto {
    @ApiModelProperty(notes = "Trạng thái", example = "200")
    private int status;

    @ApiModelProperty(notes = "Diễn giải", example = "OK")
    private String description;

    @ApiModelProperty(notes = "Thông báo", example = "")
    private String message;

    @ApiModelProperty(notes = "Danh sách response", example = "[]")
    private List<Object> response;
}
