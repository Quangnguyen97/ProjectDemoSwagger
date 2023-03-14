package com.example.demoswagger.SQLServer.BanHang;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.example.demoswagger.Module.*;
import com.example.demoswagger.SQLServer.HangHoa;
import com.example.demoswagger.SQLServer.DateFromDateTo;

@Service
public class BanHangServiceImpl implements BanHangService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<HangHoa> getListBanHang(DateFromDateTo dateFromDateTo) {
        try {
            // Check error field
            if (ResourceValid.TypeIsError(ResourceValid.typeOBJECT.STRING, dateFromDateTo.getDateFrom())) {
                throw new ResourceException(ResourceValid.StringError(ResourceValid.typeERROR.FIELD, "DateFrom"));
            } else if (ResourceValid.TypeIsError(ResourceValid.typeOBJECT.STRING, dateFromDateTo.getDateTo())) {
                throw new ResourceException(ResourceValid.StringError(ResourceValid.typeERROR.FIELD, "DateTo"));
            }

            String mDateFrom = "", mDateTo = "";
            if (ResourceValid.StrIsError(dateFromDateTo.getDateFrom())) {
                mDateFrom = "NULL";
            } else {
                mDateFrom = "'" + dateFromDateTo.getDateFrom() + "'";
            }
            if (ResourceValid.StrIsError(dateFromDateTo.getDateTo())) {
                mDateTo = "NULL";
            } else {
                mDateTo = "'" + dateFromDateTo.getDateTo() + "'";
            }

            String sql = "EXEC sp_GETTBL_ForAndroid_BanHang_HangHoa " + mDateFrom + ", " + mDateTo + "";
            return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(HangHoa.class));
        } catch (Exception e) {
            throw new ResourceException(e.getMessage());
        }
    }
}
