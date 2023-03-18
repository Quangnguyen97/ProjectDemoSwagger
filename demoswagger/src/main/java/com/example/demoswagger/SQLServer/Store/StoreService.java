package com.example.demoswagger.SQLServer.Store;

import java.util.List;

import com.example.demoswagger.SQLServer.*;

public interface StoreService {

    // Retail
    List<StoreRetail> getListStoreRetail();

    List<Store> getListRetailImport(BodyParameterSecond param);

    List<Store> getListRetailInventory(BodyParameterSecond param);

    List<Store> getListRetailExport(BodyParameterSecond param);

    // Inventory
    List<Store> getListInventory(BodyParameterFirst param);

    List<Store> getListInventoryWithStock(BodyParameterFirst param);
}
