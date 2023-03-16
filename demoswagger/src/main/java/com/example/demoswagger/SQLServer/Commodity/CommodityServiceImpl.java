package com.example.demoswagger.SQLServer.Commodity;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.example.demoswagger.Module.*;
import com.example.demoswagger.SQLServer.DateFromDateTo;

@Service
public class CommodityServiceImpl implements CommodityService {

    private String mDateFrom = "", mDateTo = "";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Commodity> getListSellCommod(DateFromDateTo dateFromDateTo) {
        try {
            // Check error field
            if (!CheckDateFromDateTo(dateFromDateTo)) {
                throw new ResourceException(
                        ResourceValid.StringError(ResourceValid.typeERROR.FIELD, "DateFrom | DateTo"));
            }
            String sql = "EXEC sp_GETTBL_ForAndroid_BanHang_HangHoa " + mDateFrom + ", " + mDateTo + "";
            return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Commodity.class));
        } catch (Exception e) {
            throw new ResourceException(e.getMessage());
        }
    }

    @Override
    public List<Commodity> getListBuyCommod(DateFromDateTo dateFromDateTo) {
        try {
            // Check error field
            if (!CheckDateFromDateTo(dateFromDateTo)) {
                throw new ResourceException(
                        ResourceValid.StringError(ResourceValid.typeERROR.FIELD, "DateFrom | DateTo"));
            }
            String sql = "EXEC sp_GETTBL_ForAndroid_MuaHang_HangHoa " + mDateFrom + ", " + mDateTo + "";
            return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Commodity.class));
        } catch (Exception e) {
            throw new ResourceException(e.getMessage());
        }
    }

    @Override
    public List<Commodity> getListImportCommod(DateFromDateTo dateFromDateTo) {
        try {
            // Check error field
            if (!CheckDateFromDateTo(dateFromDateTo)) {
                throw new ResourceException(
                        ResourceValid.StringError(ResourceValid.typeERROR.FIELD, "DateFrom | DateTo"));
            }
            String sql = "EXEC sp_GETTBL_ForAndroid_NhapTra_HangHoa " + mDateFrom + ", " + mDateTo + "";
            return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Commodity.class));
        } catch (Exception e) {
            throw new ResourceException(e.getMessage());
        }
    }

    @Override
    public List<Commodity> getListExportCommod(DateFromDateTo dateFromDateTo) {
        try {
            // Check error field
            if (!CheckDateFromDateTo(dateFromDateTo)) {
                throw new ResourceException(
                        ResourceValid.StringError(ResourceValid.typeERROR.FIELD, "DateFrom | DateTo"));
            }
            String sql = "EXEC sp_GETTBL_ForAndroid_XuatTra_HangHoa " + mDateFrom + ", " + mDateTo + "";
            return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Commodity.class));
        } catch (Exception e) {
            throw new ResourceException(e.getMessage());
        }
    }

    private boolean CheckDateFromDateTo(DateFromDateTo dateFromDateTo) {
        try {
            if (ResourceValid.TypeIsError(ResourceValid.typeOBJECT.STRING, dateFromDateTo.getDateFrom())
                    || ResourceValid.TypeIsError(ResourceValid.typeOBJECT.STRING, dateFromDateTo.getDateTo())) {
                return false;
            }
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
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
