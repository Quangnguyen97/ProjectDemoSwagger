package com.example.demoswagger.SQLServer.Debt;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DebtChart {

    private int order;
    private String code;
    private String name;

    private String value;

    private double density;
    private int codeRest;

    private List<DebtChartDetail> detail;

    public DebtChart() {
        super();
    }

    public DebtChart(int order, String code, String name, String value, double density, int codeRest) {
        super();
        this.order = order;
        this.code = code;
        this.name = name;
        this.value = value;
        this.density = density;
        this.codeRest = codeRest;
        this.detail = null;
    }

    public DebtChart(int order, String code, String name, String value, double density, int codeRest,
            List<DebtChartDetail> detail) {
        super();
        this.order = order;
        this.code = code;
        this.name = name;
        this.value = value;
        this.density = density;
        this.codeRest = codeRest;
        this.detail = detail;
    }
}
