package com.example.demoswagger.sqlserver.commodity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Commodity {

    private int order;
    private String description;
    private String quantity;
    private String amount;
    private Integer type;

    public Commodity() {
        super();
    }

    public Commodity(int order, String description, String quantity, String amount) {
        super();
        this.order = order;
        this.description = description;
        this.quantity = quantity;
        this.amount = amount;
        this.type = null;
    }

    public Commodity(int order, String description, String quantity, String amount, Integer type) {
        super();
        this.order = order;
        this.description = description;
        this.quantity = quantity;
        this.amount = amount;
        this.type = type;
    }
}
