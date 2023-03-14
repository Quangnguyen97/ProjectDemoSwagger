package com.example.demoswagger.SQLServer;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DateFromDateTo {

    private String dateFrom;
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
