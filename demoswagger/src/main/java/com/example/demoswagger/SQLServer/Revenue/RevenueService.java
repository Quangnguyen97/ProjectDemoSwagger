package com.example.demoswagger.SQLServer.Revenue;

import java.util.List;

import com.example.demoswagger.SQLServer.*;

public interface RevenueService {

    // Top chart
    List<RevenueChart> getListChartClientNegative(BodyParameterFirst param);

    List<RevenueChart> getListChartClientPositive(BodyParameterFirst param);

    List<RevenueChartDetail> getListChartClientDetail(BodyParameterFirst param);

    List<RevenueChart> getListChartClientNegativeWithDetail(BodyParameterFirst param);

    List<RevenueChart> getListChartClientPositiveWithDetail(BodyParameterFirst param);

    // Code map

    // Client
    List<RevenueMap> getListMapClientNegative(BodyParameterFirst param);

    List<RevenueMapDetail> getListMapClientNegativeDetail(BodyParameterFirst param);

    List<RevenueMap> getListMapClientNegativeWithDetail(BodyParameterFirst param);

    List<RevenueMap> getListMapClientPositive(BodyParameterFirst param);

    List<RevenueMapDetail> getListMapClientPositiveDetail(BodyParameterFirst param);

    List<RevenueMap> getListMapClientPositiveWithDetail(BodyParameterFirst param);

    // Commodity
    List<RevenueMap> getListMapCommodityNegative(BodyParameterFirst param);

    List<RevenueMapDetail> getListMapCommodityNegativeDetail(BodyParameterFirst param);

    List<RevenueMap> getListMapCommodityNegativeWithDetail(BodyParameterFirst param);

    List<RevenueMap> getListMapCommodityPositive(BodyParameterFirst param);

    List<RevenueMapDetail> getListMapCommodityPositiveDetail(BodyParameterFirst param);

    List<RevenueMap> getListMapCommodityPositiveWithDetail(BodyParameterFirst param);
}
