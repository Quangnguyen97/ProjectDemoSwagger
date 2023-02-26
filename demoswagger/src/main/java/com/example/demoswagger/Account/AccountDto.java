package com.example.demoswagger.Account;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountDto {
    @JsonProperty("userId")
    private long userId;

    @NotNull
    @JsonProperty("accountNumber")
    private long accountNumber;

    @NotNull
    @JsonProperty("accountBalance")
    private double accountBalance;
}
