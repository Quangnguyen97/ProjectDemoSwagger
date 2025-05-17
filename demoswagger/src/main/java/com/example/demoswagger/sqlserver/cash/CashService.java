package com.example.demoswagger.sqlserver.cash;

import com.example.demoswagger.sqlserver.BodyParameterFirst;

import java.util.List;

public interface CashService {

    List<CashBook> getListCashBook(BodyParameterFirst param);

    List<CashReceiptPayment> getListCashReceipt(BodyParameterFirst param);

    List<CashReceiptPayment> getListCashPayment(BodyParameterFirst param);

}
