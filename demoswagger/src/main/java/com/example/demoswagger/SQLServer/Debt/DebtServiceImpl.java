package com.example.demoswagger.SQLServer.Debt;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.example.demoswagger.Module.*;
import com.example.demoswagger.SQLServer.BodyParameterFirst;
import com.example.demoswagger.SQLServer.BodyParameterSecond;

@Service
public class DebtServiceImpl implements DeptDebtService {

    private String mDateFrom = "", mDateTo = "";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Commod
    @Override
    public List<Debt> getListCollectDebt(BodyParameterFirst param) {
        try {
            // Check error field
            if (!CheckDateFromDateTo(param)) {
                throw new ResourceException(
                        ResourceValid.StringError(ResourceValid.typeERROR.FIELD, "DateFrom | DateTo"));
            }
            String sql = "EXEC sp_GETTBL_ForAndroid_BanHang_HangHoa " + mDateFrom + ", " + mDateTo + "";
            return jdbcTemplate.query(sql, (resource, rowNum) -> new Debt(
                    resource.getInt("SapXep"),
                    resource.getString("ThongTinHangHoa"),
                    resource.getString("ThongTinHangHoa"),
                    resource.getDouble("SoLuong"),
                    resource.getInt("SoTien")));
        } catch (Exception e) {
            throw new ResourceException(e.getMessage());
        }
    }

    private boolean CheckDateFromDateTo(BodyParameterFirst param) {
        try {
            if (ResourceValid.TypeIsError(ResourceValid.typeOBJECT.STRING, param.getDateFrom())
                    || ResourceValid.TypeIsError(ResourceValid.typeOBJECT.STRING, param.getDateTo())) {
                return false;
            }
            if (ResourceValid.StrIsError(param.getDateFrom())) {
                mDateFrom = "NULL";
            } else {
                mDateFrom = "'" + param.getDateFrom() + "'";
            }
            if (ResourceValid.StrIsError(param.getDateTo())) {
                mDateTo = "NULL";
            } else {
                mDateTo = "'" + param.getDateTo() + "'";
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
