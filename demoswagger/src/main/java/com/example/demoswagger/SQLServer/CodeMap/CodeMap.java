package com.example.demoswagger.SQLServer.CodeMap;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CodeMap {

    private int order;
    private String codeType;
    private String countDetail;
    private List<CodeMapDetail> detail;

    public CodeMap() {
        super();
    }

    public CodeMap(int order, String codeType, String countDetail) {
        super();
        this.order = order;
        this.codeType = codeType;
        this.countDetail = countDetail;
        this.detail = null;
    }

    public CodeMap(int order, String codeType, String countDetail, List<CodeMapDetail> detail) {
        super();
        this.order = order;
        this.codeType = codeType;
        this.countDetail = countDetail;
        this.detail = detail;
    }
}
