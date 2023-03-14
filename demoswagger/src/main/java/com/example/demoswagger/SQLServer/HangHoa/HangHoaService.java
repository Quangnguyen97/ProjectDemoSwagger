package com.example.demoswagger.SQLServer.HangHoa;

import java.util.List;

import com.example.demoswagger.SQLServer.DateFromDateTo;

public interface HangHoaService {

    List<HangHoa> getListBanHang(DateFromDateTo dateFromDateTo);

    List<HangHoa> getListMuaHang(DateFromDateTo dateFromDateTo);

    List<HangHoa> getListNhapTra(DateFromDateTo dateFromDateTo);

    List<HangHoa> getListXuatTra(DateFromDateTo dateFromDateTo);
}
