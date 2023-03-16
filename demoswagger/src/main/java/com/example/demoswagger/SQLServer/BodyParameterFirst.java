package com.example.demoswagger.SQLServer;

import com.example.demoswagger.Module.ResourceDateTimeValid;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BodyParameterFirst {

    @ResourceDateTimeValid(fomart = "yyyy/dd/MM", message = "Invalid convert datetime to string!")
    private String dateFrom;

    @ResourceDateTimeValid(fomart = "yyyy/dd/MM", message = "Invalid convert datetime to string!")
    private String dateTo;

    private String code;
    private Integer codeRest;
    private String codeType;
    private String codeValue;

    public BodyParameterFirst() {
        super();
    }

    public BodyParameterFirst(String dateTo) {
        super();
        this.dateTo = dateTo;
    }

    public BodyParameterFirst(String dateFrom, String dateTo) {
        super();
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
    }

    public BodyParameterFirst(String dateFrom, String dateTo, String codeType) {
        super();
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.codeType = codeType;
    }

    public BodyParameterFirst(String dateFrom, String dateTo, String codeType, String codeValue) {
        super();
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.codeType = codeType;
        this.codeValue = codeValue;

    }

    public BodyParameterFirst(String dateFrom, String dateTo, String code, Integer codeRest) {
        super();
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.code = code;
        this.codeRest = codeRest;
    }

    public BodyParameterFirst(String dateFrom, String dateTo, String code, Integer codeRest, String codeType) {
        super();
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.code = code;
        this.codeRest = codeRest;
        this.codeType = codeType;
    }
}
