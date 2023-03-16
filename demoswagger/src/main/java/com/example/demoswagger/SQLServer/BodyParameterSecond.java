package com.example.demoswagger.SQLServer;

import com.example.demoswagger.Module.ResourceDateTimeValid;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BodyParameterSecond {

    @ResourceDateTimeValid(fomart = "yyyy/dd/MM", message = "Invalid convert datetime to string!")
    private String dateFrom;

    @ResourceDateTimeValid(fomart = "yyyy/dd/MM", message = "Invalid convert datetime to string!")
    private String dateTo;

    private String codeType;
    private String codeValue;
    private String user;
    private String store;

    public BodyParameterSecond() {
        super();
    }

    public BodyParameterSecond(String user) {
        super();
        this.user = user;
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

    public BodyParameterSecond(String user, String store, String dateFrom, String dateTo) {
        super();
        this.user = user;
        this.store = store;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
    }
}
