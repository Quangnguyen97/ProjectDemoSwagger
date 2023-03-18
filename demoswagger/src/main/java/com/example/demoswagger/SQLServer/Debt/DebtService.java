package com.example.demoswagger.SQLServer.Debt;

import java.util.List;

import com.example.demoswagger.SQLServer.*;

public interface DebtService {

    List<Debt> getListCollectDebt(BodyParameterFirst param);

    List<Debt> getListPayDebt(BodyParameterFirst param);

    // Top chart
    List<DebtChart> getListCollectChart(BodyParameterFirst param);

    List<DebtChartDetail> getListCollectChartDetail(BodyParameterFirst param);

    List<DebtChart> getListCollectChartWithDetail(BodyParameterFirst param);

    List<DebtChart> getListPayChart(BodyParameterFirst param);

    List<DebtChartDetail> getListPayChartDetail(BodyParameterFirst param);

    List<DebtChart> getListPayChartWithDetail(BodyParameterFirst param);

    // Code map

    // Client
    List<DebtMap> getListMapClientNegative(BodyParameterSecond param);

    List<DebtMapDetail> getListMapClientNegativeDetail(BodyParameterSecond param);

    List<DebtMap> getListMapClientNegativeWithDetail(BodyParameterSecond param);

    List<DebtMap> getListMapClientPositive(BodyParameterSecond param);

    List<DebtMapDetail> getListMapClientPositiveDetail(BodyParameterSecond param);

    List<DebtMap> getListMapClientPositiveWithDetail(BodyParameterSecond param);

    // Supplier
    List<DebtMap> getListMapSupplierNegative(BodyParameterSecond param);

    List<DebtMapDetail> getListMapSupplierNegativeDetail(BodyParameterSecond param);

    List<DebtMap> getListMapSupplierNegativeWithDetail(BodyParameterSecond param);

    List<DebtMap> getListMapSupplierPositive(BodyParameterSecond param);

    List<DebtMapDetail> getListMapSupplierPositiveDetail(BodyParameterSecond param);

    List<DebtMap> getListMapSupplierPositiveWithDetail(BodyParameterSecond param);
}
