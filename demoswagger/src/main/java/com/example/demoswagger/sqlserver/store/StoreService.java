package com.example.demoswagger.sqlserver.store;

import com.example.demoswagger.sqlserver.BodyParameterFirst;
import com.example.demoswagger.sqlserver.BodyParameterSecond;

import java.util.List;

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
