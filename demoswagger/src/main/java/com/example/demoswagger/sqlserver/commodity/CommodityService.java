package com.example.demoswagger.sqlserver.commodity;

import com.example.demoswagger.sqlserver.BodyParameterFirst;

import java.util.List;

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
