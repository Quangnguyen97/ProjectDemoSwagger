package com.example.demoswagger.SQLServer.BanHang;

import java.util.List;

import com.example.demoswagger.SQLServer.DateFromDateTo;
import com.example.demoswagger.SQLServer.HangHoa;

public interface BanHangService {

    List<HangHoa> getListBanHang(DateFromDateTo dateFromDateTo);
}
