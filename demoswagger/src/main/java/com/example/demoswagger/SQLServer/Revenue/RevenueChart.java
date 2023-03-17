package com.example.demoswagger.SQLServer.Revenue;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RevenueChart {

    private int order;
    private String code;
    private String name;
    private double value;
    private double density;
    private int codeRest;

    private List<RevenueChartDetail> detail;

    public RevenueChart() {
        super();
    }

    public RevenueChart(int order, String code, String name, double value, double density, int codeRest) {
        super();
        this.order = order;
        this.code = code;
        this.name = name;
        this.value = value;
        this.density = density;
        this.codeRest = codeRest;
        this.detail = null;
    }

    public RevenueChart(int order, String code, String name, double value, double density, int codeRest,
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
