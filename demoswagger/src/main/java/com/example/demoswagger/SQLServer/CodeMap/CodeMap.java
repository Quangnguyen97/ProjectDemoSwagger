package com.example.demoswagger.SQLServer.CodeMap;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CodeMap {

    private String codeType;
    private List<CodeMapDetail> detail;

    public CodeMap() {
        super();
    }

    public CodeMap(String codeType) {
        super();
        this.codeType = codeType;
        this.detail = null;
    }

    public CodeMap(String codeType, List<CodeMapDetail> detail) {
        super();
        this.codeType = codeType;
        this.detail = detail;
    }
}
