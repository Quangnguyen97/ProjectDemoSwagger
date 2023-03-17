package com.example.demoswagger.SQLServer.Revenue;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RevenueMap {

    private int order;
    private String code;
    private String name;
    private double value;
    private String type;
    private List<RevenueMapDetail> detail;

    public RevenueMap() {
        super();
    }

    public RevenueMap(int order, String code, String name, double value, String type) {
        super();
        this.order = order;
        this.code = code;
        this.name = name;
        this.value = value;
        this.type = type;
        this.detail = null;
    }

    public RevenueMap(int order, String code, String name, double value, String type, List<RevenueMapDetail> detail) {
        super();
        this.order = order;
        this.code = code;
        this.name = name;
        this.value = value;
        this.type = type;
        this.detail = detail;
    }
}