package com.example.demoswagger.sqlserver.codemap;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CodeMapDetail {

    private int order;
    private String codeType;
    private String codeValue;
    private String codeDescription;
    private Integer codeRest;
    private String countDetail;

    public CodeMapDetail() {
        super();
    }

    public CodeMapDetail(int order, String codeType, String codeValue, String codeDescription, Integer codeRest,
                         String countDetail) {
        super();
        this.order = order;
        this.codeType = codeType;
        this.codeValue = codeValue;
        this.codeDescription = codeDescription;
        this.codeRest = codeRest;
        this.countDetail = countDetail;
    }
}
