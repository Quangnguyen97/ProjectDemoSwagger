package com.example.demoswagger.SQLServer.Revenue;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RevenueChartDetail {

    private int order;
    private String code;
    private String name;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private Date date;

    private String value;
    private int type;

    public RevenueChartDetail() {
        super();
    }

    public RevenueChartDetail(int order, String code, String name, Date date, String value, int type) {
        super();
        this.code = code;
        this.name = name;
        this.date = date;
        this.value = value;
        this.type = type;
    }
}
