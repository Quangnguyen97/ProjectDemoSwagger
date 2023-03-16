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

    private Double density;
    private Integer codeRest;

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
        this.density = null;
        this.codeRest = null;
    }

    public Debt(int order, String code, String name, double value, Integer type) {
        super();
        this.order = order;
        this.code = code;
        this.name = name;
        this.value = value;
        this.type = type;
        this.density = null;
        this.codeRest = null;
    }

    public Debt(int order, String code, String name, double value, Double density, Integer codeRest) {
        super();
        this.order = order;
        this.code = code;
        this.name = name;
        this.value = value;
        this.type = null;
        this.density = density;
        this.codeRest = codeRest;
    }
}
