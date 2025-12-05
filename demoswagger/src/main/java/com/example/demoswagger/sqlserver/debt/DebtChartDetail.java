package com.example.demoswagger.sqlserver.debt;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
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
}
