package com.example.demoswagger.SQLServer.Commodity;

import java.util.List;

import com.example.demoswagger.SQLServer.DateFromDateTo;

public interface CommodityObjectService {

    // Client
    List<Commodity> getListSellClient(DateFromDateTo dateFromDateTo);

    List<Commodity> getListImportClient(DateFromDateTo dateFromDateTo);

    // Supplier
    List<Commodity> getListBuySupplier(DateFromDateTo dateFromDateTo);

    List<Commodity> getListExportSupplier(DateFromDateTo dateFromDateTo);

    // Counter
    List<Commodity> getListSellCounter(DateFromDateTo dateFromDateTo);
}
