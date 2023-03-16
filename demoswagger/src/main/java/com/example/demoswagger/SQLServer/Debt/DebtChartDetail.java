package com.example.demoswagger.SQLServer.Debt;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DebtChartDetail {

    private String code;
    private String name;
    private Date date;
    private double begin;
    private double incurred;
    private double pay;
    private double value;
    private Integer type;
    private Integer type2;

    public DebtChartDetail() {
        super();
    }

    public DebtChartDetail(String code, String name, Date date, double begin, double incurred, double pay,
            double value, Integer type, Integer type2) {
        super();
        this.code = code;
        this.name = name;
        this.date = date;
        this.begin = begin;
        this.incurred = incurred;
        this.pay = pay;
        this.value = value;
        this.type = type;
        this.type2 = type2;
    }
}
