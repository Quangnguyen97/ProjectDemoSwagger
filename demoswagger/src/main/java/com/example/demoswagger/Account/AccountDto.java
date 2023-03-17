package com.example.demoswagger.Account;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountDto {
    @ApiModelProperty(notes = "Số định danh", example = "1")
    @JsonProperty("userId")
    private long userId;

    @ApiModelProperty(notes = "Số tài khoản", example = "1")
    @NotNull
    @JsonProperty("accountNumber")
    private long accountNumber;

    @ApiModelProperty(notes = "Số tiền", example = "1000000")
    @NotNull
    @JsonProperty("accountBalance")
    private double accountBalance;
}
