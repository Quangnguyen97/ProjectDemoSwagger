package com.example.demoswagger.SQLServer.Debt;

import java.util.List;

// import com.example.demoswagger.SQLServer.BodyParameterSecond;
import com.example.demoswagger.SQLServer.BodyParameterFirst;

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
    // List<Debt> getListMapClientNegative(BodyParameterSecond param);

    // List<DebtMapDetail> getListMapClientNegativeDetail(BodyParameterSecond
    // param);

    // List<Debt> getListMapClientPositive(BodyParameterSecond param);

    // List<DebtMapDetail> getListMapClientPositiveDetail(BodyParameterSecond
    // param);

    // List<Debt> getListMapSupplierNegative(BodyParameterSecond param);

    // List<DebtMapDetail> getListMapSupplierNegativeDetail(BodyParameterSecond
    // param);

    // List<Debt> getListMapSupplierPositive(BodyParameterSecond param);

    // List<DebtMapDetail> getListMapSupplierPositiveDetail(BodyParameterSecond
    // param);
}
