package com.example.demoswagger.SQLServer.Store;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Store {

    private int order;
    private String description;
    private String value;
    private Integer type;

    public Store() {
        super();
    }

    public Store(int order, String description, String value) {
        super();
        this.order = order;
        this.description = description;
        this.value = value;
        this.type = null;
    }

    public Store(int order, String description, String value, Integer type) {
        super();
        this.order = order;
        this.description = description;
        this.value = value;
        this.type = type;
    }
}
