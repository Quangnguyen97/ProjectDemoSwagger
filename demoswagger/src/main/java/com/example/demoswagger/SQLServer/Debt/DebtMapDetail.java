package com.example.demoswagger.SQLServer.Debt;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DebtMapDetail {

    private int order;
    private String description;

    private String value;

    public DebtMapDetail() {
        super();
    }

    public DebtMapDetail(int order, String description, String value) {
        super();
        this.order = order;
        this.description = description;
        this.value = value;
    }
}
