package com.example.demoswagger.SQLServer.Commodity;

import java.util.List;

import com.example.demoswagger.SQLServer.DateFromDateTo;

public interface CommodityService {

    // Commod
    List<Commodity> getListSellCommod(DateFromDateTo dateFromDateTo);

    List<Commodity> getListBuyCommod(DateFromDateTo dateFromDateTo);

    List<Commodity> getListImportCommod(DateFromDateTo dateFromDateTo);

    List<Commodity> getListExportCommod(DateFromDateTo dateFromDateTo);
}
