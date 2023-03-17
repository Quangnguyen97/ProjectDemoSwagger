package com.example.demoswagger.SQLServer.Debt;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DebtMap {

    private int order;
    private String code;
    private String name;
    private double value;
    private String type;
    private List<DebtMapDetail> detail;

    public DebtMap() {
        super();
    }

    public DebtMap(int order, String code, String name, double value, String type) {
        super();
        this.order = order;
        this.code = code;
        this.name = name;
        this.value = value;
        this.type = type;
        this.detail = null;
    }

    public DebtMap(int order, String code, String name, double value, String type, List<DebtMapDetail> detail) {
        super();
        this.order = order;
        this.code = code;
        this.name = name;
        this.value = value;
        this.type = type;
        this.detail = detail;
    }
}
