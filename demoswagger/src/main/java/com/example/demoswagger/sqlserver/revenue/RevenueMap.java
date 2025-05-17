package com.example.demoswagger.sqlserver.revenue;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RevenueMap {

    private int order;
    private String code;
    private String name;
    private String value;
    private String codeType;
    private List<RevenueMapDetail> detail;

    public RevenueMap() {
        super();
    }

    public RevenueMap(int order, String code, String name, String value, String codeType) {
        super();
        this.order = order;
        this.code = code;
        this.name = name;
        this.value = value;
        this.codeType = codeType;
        this.detail = null;
    }

    public RevenueMap(int order, String code, String name, String value, String codeType,
                      List<RevenueMapDetail> detail) {
        super();
        this.order = order;
        this.code = code;
        this.name = name;
        this.value = value;
        this.codeType = codeType;
        this.detail = detail;
    }
}