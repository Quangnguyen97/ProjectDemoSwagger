package com.example.demoswagger.sqlserver.debt;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Debt {

    private int order;
    private String code;
    private String name;

    private String value;

    private int type;

    public Debt() {
        super();
    }

    public Debt(int order, String code, String name, String value, int type) {
        super();
        this.order = order;
        this.code = code;
        this.name = name;
        this.value = value;
        this.type = type;
    }
}
