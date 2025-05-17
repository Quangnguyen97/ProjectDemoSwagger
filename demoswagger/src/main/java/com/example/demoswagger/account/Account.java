package com.example.demoswagger.account;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@Table(name = "tbl_Account")
@Getter
@Setter
@NoArgsConstructor
public class Account {

    @NotNull
    @Min(value = 1)
    @Column(name = "account_user_id", insertable = true, updatable = true)
    private long userId;

    @NotNull
    @Min(value = 1)
    @Column(name = "account_number")
    private @Id long accountNumber;

    @NotNull
    @Column(name = "account_balance")
    private double accountBalance;

    public Account(long userId, long accountNumber, double accountBalance) {
        super();
        this.userId = userId;
        this.accountNumber = accountNumber;
        this.accountBalance = accountBalance;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object)
            return true;
        if (!(object instanceof Account account))
            return false;
        return Objects.equals(this.accountNumber, account.accountNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.accountNumber);
    }
}
