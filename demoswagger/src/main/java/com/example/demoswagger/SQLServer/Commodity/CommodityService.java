package com.example.demoswagger.SQLServer.Commodity;

import java.util.List;

import com.example.demoswagger.SQLServer.*;

public interface CommodityService {

    // Commod
    List<Commodity> getListSellCommod(BodyParameterFirst param);

    List<Commodity> getListBuyCommod(BodyParameterFirst param);

    List<Commodity> getListImportCommod(BodyParameterFirst param);

    List<Commodity> getListExportCommod(BodyParameterFirst param);

    // Client
    List<Commodity> getListSellClient(BodyParameterFirst param);

    List<Commodity> getListImportClient(BodyParameterFirst param);

    // Supplier
    List<Commodity> getListBuySupplier(BodyParameterFirst param);

    List<Commodity> getListExportSupplier(BodyParameterFirst param);

    // Counter
    List<Commodity> getListSellCounter(BodyParameterFirst param);
}
