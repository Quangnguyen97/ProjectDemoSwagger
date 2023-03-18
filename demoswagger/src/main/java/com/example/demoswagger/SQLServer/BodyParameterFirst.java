package com.example.demoswagger.SQLServer;

import com.example.demoswagger.Module.ResourceDateTimeValid;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BodyParameterFirst {

    @ResourceDateTimeValid(fomart = "yyyy/dd/MM", message = "Invalid convert datetime to string!")
    private String dateFrom = null;

    @ResourceDateTimeValid(fomart = "yyyy/dd/MM", message = "Invalid convert datetime to string!")
    private String dateTo = null;

    private String code = null;
    private Integer codeRest = null;
    private String codeType = null;
    private String codeValue = null;

    private Integer type = null;

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

    public BodyParameterFirst(String dateFrom, String dateTo, String code, Integer codeRest, Integer type) {
        super();
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.code = code;
        this.codeRest = codeRest;
        this.type = type;
    }
}
