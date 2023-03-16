package com.example.demoswagger.SQLServer.Commodity;

import java.util.List;

import com.example.demoswagger.SQLServer.BodyParameter;

public interface CommodityService {

    // Commod
    List<Commodity> getListSellCommod(BodyParameter bodyParameter);

    List<Commodity> getListBuyCommod(BodyParameter bodyParameter);

    List<Commodity> getListImportCommod(BodyParameter bodyParameter);

    List<Commodity> getListExportCommod(BodyParameter bodyParameter);

    // Client
    List<Commodity> getListSellClient(BodyParameter bodyParameter);

    List<Commodity> getListImportClient(BodyParameter bodyParameter);

    // Supplier
    List<Commodity> getListBuySupplier(BodyParameter bodyParameter);

    List<Commodity> getListExportSupplier(BodyParameter bodyParameter);

    // Counter
    List<Commodity> getListSellCounter(BodyParameter bodyParameter);
}
