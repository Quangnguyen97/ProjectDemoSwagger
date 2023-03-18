package com.example.demoswagger.SQLServer.Cash;

import java.util.List;

import com.example.demoswagger.SQLServer.BodyParameterFirst;

public interface CashService {

    List<CashBook> getListCashBook(BodyParameterFirst param);

    List<CashReceiptPayment> getListCashReceipt(BodyParameterFirst param);

    List<CashReceiptPayment> getListCashPayment(BodyParameterFirst param);

}
