package com.example.demoswagger.SQLServer.Commodity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Commodity {

    private int dataOrder;
    private String dataDescription;
    private double dataQuantity;
    private double dataAmount;

    public Commodity() {
    }

    public Commodity(int dataOrder, String dataDescription, String notificationToken, double dataQuantity,
            double dataAmount) {
        super();
        this.dataOrder = dataOrder;
        this.dataDescription = dataDescription;
        this.dataQuantity = dataQuantity;
        this.dataAmount = dataAmount;
    }

    public int getDataOrder() {
        return dataOrder;
    }

    public void setDataOrder(int dataOrder) {
        this.dataOrder = dataOrder;
    }

    public String getDataDescription() {
        return dataDescription;
    }

    public void setDataDescription(String dataDescription) {
        this.dataDescription = dataDescription;
    }

    public double getDataQuantity() {
        return dataQuantity;
    }

    public void setDataQuantity(double dataQuantity) {
        this.dataQuantity = dataQuantity;
    }

    public double getDataAmount() {
        return dataAmount;
    }

    public void setDataAmount(double dataAmount) {
        this.dataAmount = dataAmount;
    }
}
