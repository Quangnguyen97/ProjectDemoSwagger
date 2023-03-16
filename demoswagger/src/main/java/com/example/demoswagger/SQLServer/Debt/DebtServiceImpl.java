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

    private String mDateFrom, mDateTo, mCode = "";
    private Integer mCodeRest;

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

    @Override
    public List<Debt> getListPayDebt(BodyParameterFirst param) {
        try {
            // Check error field
            if (!CheckDateTo(param)) {
                throw new ResourceException(
                        ResourceValid.StringError(ResourceValid.typeERROR.FIELD, "DateTo"));
            }
            String sql = "EXEC sp_GETTBL_ForAndroid_CongNoTra " + mDateTo + "";
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

    @Override
    public List<Debt> getListCollectChart(BodyParameterFirst param) {
        try {
            // Check error field
            if (!CheckDateTo(param)) {
                throw new ResourceException(
                        ResourceValid.StringError(ResourceValid.typeERROR.FIELD, "DateTo"));
            }
            String sql = "EXEC sp_GETTBL_ForAndroid_TopChart_CongNoThu " + mDateTo + "";
            return jdbcTemplate.query(sql, (resource, rowNum) -> new Debt(
                    resource.getInt("SapXep"),
                    resource.getString("MaDoiTuong"),
                    resource.getString("ThongTinDoiTuong"),
                    resource.getDouble("SoTien"),
                    resource.getDouble("TyTrong"),
                    resource.getInt("IsCodeRest")));
        } catch (Exception e) {
            throw new ResourceException(e.getMessage());
        }
    }

    @Override
    public List<DebtChartDetail> getListCollectChartDetail(BodyParameterFirst param) {
        try {
            // Check error field
            if (!CheckDateFrom(param) || !CheckDateTo(param) || !CheckCode(param) || !CheckCodeRest(param)) {
                throw new ResourceException(
                        ResourceValid.StringError(ResourceValid.typeERROR.FIELD, ""));
            }
            String sql = "EXEC sp_GETTBL_ForAndroid_CongNoThu_CT "
                    + mDateFrom + ", " + mDateTo + ", " + mCode + ", " + mCodeRest + "";
            return jdbcTemplate.query(sql, (resource, rowNum) -> new DebtChartDetail(
                    resource.getString("MaDoiTuong"),
                    resource.getString("TenDoiTuong"),
                    resource.getDate("NgayCTu"),
                    resource.getDouble("SoDuDK"),
                    resource.getDouble("PhatSinhNo"),
                    resource.getDouble("ThanhToan"),
                    resource.getDouble("SoDuCK"),
                    resource.getInt("Loai"),
                    resource.getInt("Loai2")));
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

    private boolean CheckDateFrom(BodyParameterFirst param) {
        try {
            if (ResourceValid.TypeIsError(ResourceValid.typeOBJECT.STRING, param.getDateFrom())) {
                return false;
            }
            if (ResourceValid.StrIsError(param.getDateFrom())) {
                mDateFrom = "NULL";
            } else {
                mDateFrom = "'" + param.getDateFrom() + "'";
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private boolean CheckCode(BodyParameterFirst param) {
        try {
            if (ResourceValid.TypeIsError(ResourceValid.typeOBJECT.STRING, param.getCode())) {
                return false;
            }
            if (ResourceValid.StrIsError(param.getCode())) {
                mCode = "NULL";
            } else {
                mCode = "'" + param.getCode() + "'";
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private boolean CheckCodeRest(BodyParameterFirst param) {
        try {
            if (ResourceValid.TypeIsError(ResourceValid.typeOBJECT.INTEGER, param.getCodeRest())) {
                return false;
            }
            if (ResourceValid.StrIsError(String.valueOf(param.getCodeRest()))) {
                mCodeRest = 0;
            } else {
                mCodeRest = param.getCodeRest();
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
