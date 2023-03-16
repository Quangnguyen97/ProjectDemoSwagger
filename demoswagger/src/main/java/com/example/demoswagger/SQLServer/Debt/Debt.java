package com.example.demoswagger.SQLServer.Debt;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Debt {

    private int order;
    private String code;
    private String name;
    private double value;
    private Integer type;

    public Debt() {
        super();
    }

    public Debt(int order, String code, String name, double value) {
        super();
        this.order = order;
        this.code = code;
        this.name = name;
        this.value = value;
        this.type = null;
    }

    public Debt(int order, String code, String name, double value, Integer type) {
        super();
        this.order = order;
        this.code = code;
        this.name = name;
        this.value = value;
        this.type = type;
    }
}
