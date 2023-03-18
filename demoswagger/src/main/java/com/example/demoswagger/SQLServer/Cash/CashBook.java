package com.example.demoswagger.SQLServer.Cash;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CashBook {

    private int order;
    private String description;

    private String receipt;
    private String payment;
    private String remaining;

    private int type;

    public CashBook() {
        super();
    }

    public CashBook(int order, String description, String receipt, String payment, String remaining, int type) {
        super();
        this.order = order;
        this.description = description;
        this.receipt = receipt;
        this.payment = payment;
        this.remaining = remaining;
        this.type = type;
    }
}
