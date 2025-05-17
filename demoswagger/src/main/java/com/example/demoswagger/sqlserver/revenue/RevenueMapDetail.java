package com.example.demoswagger.sqlserver.revenue;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RevenueMapDetail {

    private int order;
    private String description;
    private String value;

    public RevenueMapDetail() {
        super();
    }

    public RevenueMapDetail(int order, String description, String value) {
        super();
        this.order = order;
        this.description = description;
        this.value = value;
    }
}
