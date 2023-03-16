package com.example.demoswagger.SQLServer.Debt;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.example.demoswagger.Module.*;
import com.example.demoswagger.SQLServer.BodyParameterFirst;
import com.example.demoswagger.SQLServer.BodyParameterSecond;

@Service
public class DebtServiceImpl implements DebtService {

    private String mDateFrom = "", mDateTo = "";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Debt> getListCollectDebt(BodyParameterFirst param) {
        try {
            // Check error field
            if (!CheckDateTo(param)) {
                throw new ResourceException(
                        ResourceValid.StringError(ResourceValid.typeERROR.FIELD, "DateTo"));
            }
            String sql = "EXEC sp_GETTBL_ForAndroid_CongNoThu " + mDateTo + "";
            return jdbcTemplate.query(sql, (resource, rowNum) -> new Debt(
                    resource.getInt("SapXep"),
                    resource.getString("MaDoiTuong"),
                    resource.getString("ThongTinDoiTuong"),
                    resource.getDouble("SoTien"),
                    resource.getInt("Loai")));
        } catch (Exception e) {
            throw new ResourceException(e.getMessage());
        }
    }

    private boolean CheckDateTo(BodyParameterFirst param) {
        try {
            if (ResourceValid.TypeIsError(ResourceValid.typeOBJECT.STRING, param.getDateTo())) {
                return false;
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
