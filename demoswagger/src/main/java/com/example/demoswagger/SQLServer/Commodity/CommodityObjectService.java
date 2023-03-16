package com.example.demoswagger.SQLServer.Commodity;

import java.util.List;

import com.example.demoswagger.SQLServer.DateFromDateTo;

public interface CommodityObjectService {

    // Client
    List<CommodityObject> getListSellClient(DateFromDateTo dateFromDateTo);

    List<CommodityObject> getListImportClient(DateFromDateTo dateFromDateTo);

    // Supplier
    List<CommodityObject> getListBuySupplier(DateFromDateTo dateFromDateTo);

    List<CommodityObject> getListExportSupplier(DateFromDateTo dateFromDateTo);

    // Counter
    List<CommodityObject> getListSellCounter(DateFromDateTo dateFromDateTo);
}
