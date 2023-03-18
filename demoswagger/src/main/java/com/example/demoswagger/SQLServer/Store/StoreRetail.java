package com.example.demoswagger.SQLServer.Store;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StoreRetail {

    private int order;
    private String code;
    private String name;
    private String description;
    private String address;

    public StoreRetail() {
        super();
    }

    public StoreRetail(int order, String code, String name, String description, String address) {
        super();
        this.order = order;
        this.code = code;
        this.name = name;
        this.description = description;
        this.address = address;
    }
}