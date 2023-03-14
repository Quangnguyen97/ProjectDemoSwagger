package com.example.demoswagger.SQLServer;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DateFromDateTo {

    @JsonFormat(pattern = "yyyy/MM/dd")
    private String dateFrom;

    @JsonFormat(pattern = "yyyy/MM/dd")
    private String dateTo;

    public DateFromDateTo() {
    }

    public DateFromDateTo(String dateFrom, String dateTo) {
        super();
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
    }

    public String getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(String dateFrom) {
        this.dateFrom = dateFrom;
    }

    public String getDateTo() {
        return dateTo;
    }

    public void setDateTo(String dateTo) {
        this.dateTo = dateTo;
    }
}
