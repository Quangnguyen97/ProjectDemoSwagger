package com.example.demoswagger.SQLServer.CodeMap;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CodeMapDetail {

    private int order;
    private String codeValue;
    private String CodeDescription;
    private Integer CodeRest;
    private String countDetail;

    public CodeMapDetail() {
        super();
    }

    public CodeMapDetail(int order, String codeValue, String CodeDescription, Integer CodeRest, String countDetail) {
        super();
        this.order = order;
        this.codeValue = codeValue;
        this.CodeDescription = CodeDescription;
        this.CodeRest = CodeRest;
        this.countDetail = countDetail;
    }
}
