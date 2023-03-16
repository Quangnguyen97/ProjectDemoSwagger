package com.example.demoswagger.SQLServer.Commodity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Commodity {

    private int order;
    private String description;
    private double quantity;
    private double amount;
    private Integer type;

    public Commodity() {
        super();
    }

    public Commodity(int order, String description, double quantity, double amount) {
        super();
        this.order = order;
        this.description = description;
        this.quantity = quantity;
        this.amount = amount;
        this.type = null;
    }

    public Commodity(int order, String description, double quantity, double amount, Integer type) {
        super();
        this.order = order;
        this.description = description;
        this.quantity = quantity;
        this.amount = amount;
        this.type = type;
    }
}
