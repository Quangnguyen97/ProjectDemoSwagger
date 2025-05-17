package com.example.demoswagger.notification;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "tbl_Notification")
@Getter
@Setter
public class Notification {

    @NotNull
    @Min(value = 1)
    @Column(name = "notification_user_id")
    private long userId;

    @NotNull
    @NotEmpty(message = "fullName must not be empty")
    @Column(name = "full_name")
    private String fullName;

    @NotNull
    @NotEmpty(message = "notificationToken must not be empty")
    @Column(name = "notification_token")
    private String notificationToken;

    @NotNull
    @Min(value = 1)
    @Column(name = "account_number")
    private @Id long accountNumber;

    @NotNull
    @Column(name = "account_balance")
    private double accountBalance;

    public Notification() {
    }

    public Notification(long userId, String fullName, String notificationToken, long accountNumber,
                        double accountBalance) {
        super();
        this.userId = userId;
        this.fullName = fullName;
        this.notificationToken = notificationToken;
        this.accountNumber = accountNumber;
        this.accountBalance = accountBalance;
    }
}
