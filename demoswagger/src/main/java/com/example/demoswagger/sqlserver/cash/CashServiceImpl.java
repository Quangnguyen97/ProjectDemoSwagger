package com.example.demoswagger.sqlserver.cash;

import com.example.demoswagger.module.ResourceException;
import com.example.demoswagger.module.ResourceValid;
import com.example.demoswagger.sqlserver.BodyParameterFirst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CashServiceImpl implements CashService {

    private String mDateFrom, mDateTo;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<CashBook> getListCashBook(BodyParameterFirst param) {
        try {
            // Check error field
            if (!CheckDateFrom(param.getDateFrom()) || !CheckDateTo(param.getDateTo())) {
                throw new ResourceException(
                        ResourceValid.StringError(ResourceValid.typeERROR.FIELD, ""));
            }
            String sql = "EXEC sp_GETTBL_ForAndroid_SoQuy " + mDateFrom + ", " + mDateTo + "";
            return jdbcTemplate.query(sql, (resource, rowNum) -> new CashBook(
                    resource.getInt("SapXep"),
                    resource.getString("ThongTinHangMuc"),
                    ResourceValid.FormatDouble(resource.getDouble("SoTienThu")),
                    ResourceValid.FormatDouble(resource.getDouble("SoTienChi")),
                    ResourceValid.FormatDouble(resource.getDouble("ConLai")),
                    resource.getInt("Loai")));
        } catch (Exception e) {
            throw new ResourceException(e.getMessage());
        }
    }

    @Override
    public List<CashReceiptPayment> getListCashReceipt(BodyParameterFirst param) {
        try {
            // Check error field
            if (!CheckDateFrom(param.getDateFrom()) || !CheckDateTo(param.getDateTo())) {
                throw new ResourceException(
                        ResourceValid.StringError(ResourceValid.typeERROR.FIELD, ""));
            }
            String sql = "EXEC sp_GETTBL_ForAndroid_ThuTien " + mDateFrom + ", " + mDateTo + "";
            return jdbcTemplate.query(sql, (resource, rowNum) -> new CashReceiptPayment(
                    resource.getInt("SapXep"),
                    resource.getString("ThongTinHangMuc"),
                    resource.getString("DienGiai"),
                    ResourceValid.FormatDouble(resource.getDouble("SoTien"))));
        } catch (Exception e) {
            throw new ResourceException(e.getMessage());
        }
    }

    @Override
    public List<CashReceiptPayment> getListCashPayment(BodyParameterFirst param) {
        try {
            // Check error field
            if (!CheckDateFrom(param.getDateFrom()) || !CheckDateTo(param.getDateTo())) {
                throw new ResourceException(
                        ResourceValid.StringError(ResourceValid.typeERROR.FIELD, ""));
            }
            String sql = "EXEC sp_GETTBL_ForAndroid_ChiTien " + mDateFrom + ", " + mDateTo + "";
            return jdbcTemplate.query(sql, (resource, rowNum) -> new CashReceiptPayment(
                    resource.getInt("SapXep"),
                    resource.getString("ThongTinHangMuc"),
                    resource.getString("DienGiai"),
                    ResourceValid.FormatDouble(resource.getDouble("SoTien"))));
        } catch (Exception e) {
            throw new ResourceException(e.getMessage());
        }
    }

    private boolean CheckDateFrom(String dateFrom) {
        try {
            if (ResourceValid.TypeIsError(ResourceValid.typeOBJECT.STRING, dateFrom)) {
                return false;
            }
            if (ResourceValid.StrIsError(dateFrom)) {
                mDateFrom = "NULL";
            } else {
                mDateFrom = "'" + dateFrom + "'";
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private boolean CheckDateTo(String dateTo) {
        try {
            if (ResourceValid.TypeIsError(ResourceValid.typeOBJECT.STRING, dateTo)) {
                return false;
            }
            if (ResourceValid.StrIsError(dateTo)) {
                mDateTo = "NULL";
            } else {
                mDateTo = "'" + dateTo + "'";
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
