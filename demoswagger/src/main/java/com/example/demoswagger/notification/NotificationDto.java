package com.example.demoswagger.notification;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class NotificationDto {
    @JsonProperty("userId")
    private long userId;

    @NotNull
    @NotEmpty(message = "fullName must not be empty")
    @JsonProperty("fullName")
    private String fullName;

    @NotNull
    @NotEmpty(message = "notificationToken must not be empty")
    @JsonProperty("notificationToken")
    private String notificationToken;

    @NotNull
    @JsonProperty("accountNumber")
    private long accountNumber;

    @NotNull
    @JsonProperty("accountBalance")
    private double accountBalance;
}
