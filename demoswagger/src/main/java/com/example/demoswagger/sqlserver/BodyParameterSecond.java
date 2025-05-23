package com.example.demoswagger.sqlserver;

import com.example.demoswagger.module.ResourceDateTimeValid;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BodyParameterSecond {

    @ResourceDateTimeValid(fomart = "yyyy/dd/MM", message = "Invalid convert datetime to string!")
    private String dateFrom = null;

    @ResourceDateTimeValid(fomart = "yyyy/dd/MM", message = "Invalid convert datetime to string!")
    private String dateTo = null;

    private String codeType = null;
    private String codeValue = null;
    private String user = null;
    private String store = null;

    public BodyParameterSecond() {
        super();
    }

    public BodyParameterSecond(String codeType) {
        super();
        this.codeType = codeType;
    }

    public BodyParameterSecond(String dateTo, String codeType) {
        super();
        this.dateTo = dateTo;
        this.codeType = codeType;
    }

    public BodyParameterSecond(String dateTo, String codeType, String codeValue) {
        super();
        this.dateTo = dateTo;
        this.codeType = codeType;
        this.codeValue = codeValue;
    }

    public BodyParameterSecond(String dateFrom, String dateTo, String user, String store) {
        super();
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.user = user;
        this.store = store;
    }
}
