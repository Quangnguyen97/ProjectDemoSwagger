package com.example.demoswagger.User;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
    @ApiModelProperty(notes = "Mã định danh", example = "1")
    @JsonProperty("userId")
    private long userId;

    @ApiModelProperty(notes = "Tên đầy đủ", example = "Nguyễn Văn A")
    @NotNull
    @NotEmpty(message = "fullName must not be empty")
    @JsonProperty("fullName")
    private String fullName;

    @ApiModelProperty(notes = "Mật khẩu", example = "123456")
    @NotNull
    @NotEmpty(message = "password must not be empty")
    @JsonProperty("password")
    private String password;

    @ApiModelProperty(notes = "Mã điện tử", example = "Tokenabc123")
    @NotNull
    @NotEmpty(message = "notificationToken must not be empty")
    @JsonProperty("notificationToken")
    private String notificationToken;
}
