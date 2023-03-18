package com.example.demoswagger.SQLServer.Cash;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CashReceiptPayment {

    private int order;
    private String code;
    private String name;

    private String value;

    public CashReceiptPayment() {
        super();
    }

    public CashReceiptPayment(int order, String code, String name, String value) {
        super();
        this.order = order;
        this.code = code;
        this.name = name;
        this.value = value;
    }
}
