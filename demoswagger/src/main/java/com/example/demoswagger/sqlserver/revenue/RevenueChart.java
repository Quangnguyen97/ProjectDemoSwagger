package com.example.demoswagger.sqlserver.revenue;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RevenueChart {

    private int order;
    private String code;
    private String name;
    private String value;
    private double density;
    private int codeRest;

    private List<RevenueChartDetail> detail;

    public RevenueChart() {
        super();
    }

    public RevenueChart(int order, String code, String name, String value, double density, int codeRest) {
        super();
        this.order = order;
        this.code = code;
        this.name = name;
        this.value = value;
        this.density = density;
        this.codeRest = codeRest;
        this.detail = null;
    }

    public RevenueChart(int order, String code, String name, String value, double density, int codeRest,
                        List<RevenueChartDetail> detail) {
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
