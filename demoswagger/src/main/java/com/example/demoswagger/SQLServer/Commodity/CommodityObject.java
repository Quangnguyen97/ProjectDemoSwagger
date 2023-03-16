package com.example.demoswagger.SQLServer.Commodity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommodityObject {

    private int order;
    private String description;
    private double quantity;
    private double amount;
    private int type;

    public CommodityObject() {
    }

    public CommodityObject(int order, String description, double quantity, double amount, int type) {
        super();
        this.order = order;
        this.description = description;
        this.quantity = quantity;
        this.amount = amount;
        this.type = type;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

}
