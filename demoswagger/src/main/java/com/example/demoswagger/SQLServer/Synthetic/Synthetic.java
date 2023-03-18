package com.example.demoswagger.SQLServer.Synthetic;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Synthetic {

    private int order;
    private String description;
    private String value;
    private Integer type;

    public Synthetic() {
        super();
    }

    public Synthetic(int order, String description, String value, Integer type) {
        super();
        this.order = order;
        this.description = description;
        this.value = value;
        this.type = type;
    }
}
