package com.example.demoswagger.SQLServer.Commodity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommodityObject {

    private int dataOrder;
    private String dataDescription;
    private double dataQuantity;
    private double dataAmount;
    private int dataType;

    public CommodityObject() {
    }

    public CommodityObject(int dataOrder, String dataDescription, String notificationToken, double dataQuantity,
            double dataAmount, int dataType) {
        super();
        this.dataOrder = dataOrder;
        this.dataDescription = dataDescription;
        this.dataQuantity = dataQuantity;
        this.dataAmount = dataAmount;
        this.dataType = dataType;
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

    public int getDataType() {
        return dataType;
    }

    public void setDataType(int dataType) {
        this.dataType = dataType;
    }

}
