package com.example.demoswagger.sqlserver.debt;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class DebtChartDetail {

    private String code;
    private String name;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private Date date;

    private String begin;
    private String incurred;
    private String pay;
    private String value;

    private int type;
    private int type2;

    public DebtChartDetail() {
        super();
    }

    public DebtChartDetail(String code, String name, Date date, String begin, String incurred, String pay,
                           String value, int type, int type2) {
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
