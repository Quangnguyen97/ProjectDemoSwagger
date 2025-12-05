package com.example.demoswagger.sqlserver.debt;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class DebtMap {

    private int order;
    private String code;
    private String name;

    private String value;

    private String codeType;
    private List<DebtMapDetail> detail;

    public DebtMap() {
        super();
    }

    public DebtMap(int order, String code, String name, String value, String codeType) {
        super();
        this.order = order;
        this.code = code;
        this.name = name;
        this.value = value;
        this.codeType = codeType;
        this.detail = null;
    }
}
