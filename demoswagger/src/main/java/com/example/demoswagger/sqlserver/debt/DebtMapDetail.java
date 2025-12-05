package com.example.demoswagger.sqlserver.debt;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class DebtMapDetail {

    private int order;
    private String description;

    private String value;

    public DebtMapDetail() {
        super();
    }
}
