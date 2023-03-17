package com.example.demoswagger.SQLServer.Debt;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.example.demoswagger.Module.*;
import com.example.demoswagger.SQLServer.*;

@Service
public class DebtServiceImpl implements DebtService {

    private String mDateFrom, mDateTo, mCode, mCodeValue, mCodeType;
    private Integer mCodeRest;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Debt> getListCollectDebt(BodyParameterFirst param) {
        try {
            // Check error field
            if (!CheckDateTo(param.getDateTo())) {
                throw new ResourceException(
                        ResourceValid.StringError(ResourceValid.typeERROR.FIELD, "DateTo"));
            }
            String sql = "EXEC sp_GETTBL_ForAndroid_CongNoThu " + mDateTo + "";
            return jdbcTemplate.query(sql, (resource, rowNum) -> new Debt(
                    resource.getInt("SapXep"),
                    resource.getString("MaDoiTuong"),
                    resource.getString("ThongTinDoiTuong"),
                    ResourceValid.FormatDouble(resource.getDouble("SoTien")),
                    resource.getInt("Loai")));
        } catch (Exception e) {
            throw new ResourceException(e.getMessage());
        }
    }

    @Override
    public List<Debt> getListPayDebt(BodyParameterFirst param) {
        try {
            // Check error field
            if (!CheckDateTo(param.getDateTo())) {
                throw new ResourceException(
                        ResourceValid.StringError(ResourceValid.typeERROR.FIELD, "DateTo"));
            }
            String sql = "EXEC sp_GETTBL_ForAndroid_CongNoTra " + mDateTo + "";
            return jdbcTemplate.query(sql, (resource, rowNum) -> new Debt(
                    resource.getInt("SapXep"),
                    resource.getString("MaDoiTuong"),
                    resource.getString("ThongTinDoiTuong"),
                    ResourceValid.FormatDouble(resource.getDouble("SoTien")),
                    resource.getInt("Loai")));
        } catch (Exception e) {
            throw new ResourceException(e.getMessage());
        }
    }

    // Top chart
    @Override
    public List<DebtChart> getListCollectChart(BodyParameterFirst param) {
        try {
            // Check error field
            if (!CheckDateTo(param.getDateTo())) {
                throw new ResourceException(
                        ResourceValid.StringError(ResourceValid.typeERROR.FIELD, "DateTo"));
            }
            String sql = "EXEC sp_GETTBL_ForAndroid_TopChart_CongNoThu " + mDateTo + "";
            List<DebtChart> listResponse = jdbcTemplate.query(sql, (resource, rowNum) -> new DebtChart(
                    resource.getInt("SapXep"),
                    resource.getString("MaDoiTuong"),
                    resource.getString("ThongTinDoiTuong"),
                    ResourceValid.FormatDouble(resource.getDouble("SoTien")),
                    resource.getDouble("TyTrong"),
                    resource.getInt("IsCodeRest")));
            if (listResponse.stream()
                    .max(Comparator.comparing(DebtChart::getCodeRest))
                    .orElseThrow(NoSuchElementException::new).getCodeRest() == 1) {
                String mCode = "";
                for (DebtChart response : listResponse) {
                    if (response.getCodeRest() == 0) {
                        if (mCode != "") {
                            mCode = mCode + "," + response.getCode();
                        } else {
                            mCode = response.getCode();
                        }
                    }
                }
                List<DebtChart> listReturn = new ArrayList<DebtChart>();
                for (DebtChart response : listResponse) {
                    if (response.getCodeRest() == 1) {
                        response.setCode(mCode);
                    }
                    listReturn.add(response);
                }
                return listReturn;
            } else {
                return listResponse;
            }
        } catch (Exception e) {
            throw new ResourceException(e.getMessage());
        }
    }

    @Override
    public List<DebtChartDetail> getListCollectChartDetail(BodyParameterFirst param) {
        try {
            // Check error field
            if (!CheckDateFrom(param.getDateFrom()) || !CheckDateTo(param.getDateTo())
                    || !CheckCode(param.getCode()) || !CheckCodeRest(param.getCodeRest())) {
                throw new ResourceException(
                        ResourceValid.StringError(ResourceValid.typeERROR.FIELD, ""));
            }
            String sql = "EXEC sp_GETTBL_ForAndroid_CongNoThu_CT "
                    + mDateFrom + ", " + mDateTo + ", " + mCode + ", " + mCodeRest + "";
            return jdbcTemplate.query(sql, (resource, rowNum) -> new DebtChartDetail(
                    resource.getString("MaDoiTuong"),
                    resource.getString("TenDoiTuong"),
                    resource.getDate("NgayCTu"),
                    ResourceValid.FormatDouble(resource.getDouble("SoDuDK")),
                    ResourceValid.FormatDouble(resource.getDouble("PhatSinhNo")),
                    ResourceValid.FormatDouble(resource.getDouble("ThanhToan")),
                    ResourceValid.FormatDouble(resource.getDouble("SoDuCK")),
                    resource.getInt("Loai"),
                    resource.getInt("Loai2")));
        } catch (Exception e) {
            throw new ResourceException(e.getMessage());
        }
    }

    @Override
    public List<DebtChart> getListCollectChartWithDetail(BodyParameterFirst param) {
        try {
            List<DebtChart> listResponse = getListCollectChart(param);
            for (DebtChart response : listResponse) {
                BodyParameterFirst paramDetail = new BodyParameterFirst(
                        "1900/01/01",
                        param.getDateTo(),
                        response.getCode(),
                        response.getCodeRest());
                response.setDetail(getListCollectChartDetail(paramDetail));
            }
            return listResponse;
        } catch (Exception e) {
            throw new ResourceException(e.getMessage());
        }
    }

    @Override
    public List<DebtChart> getListPayChart(BodyParameterFirst param) {
        try {
            // Check error field
            if (!CheckDateTo(param.getDateTo())) {
                throw new ResourceException(
                        ResourceValid.StringError(ResourceValid.typeERROR.FIELD, "DateTo"));
            }
            String sql = "EXEC sp_GETTBL_ForAndroid_TopChart_CongNoTra " + mDateTo + "";
            List<DebtChart> listResponse = jdbcTemplate.query(sql, (resource, rowNum) -> new DebtChart(
                    resource.getInt("SapXep"),
                    resource.getString("MaDoiTuong"),
                    resource.getString("ThongTinDoiTuong"),
                    ResourceValid.FormatDouble(resource.getDouble("SoTien")),
                    resource.getDouble("TyTrong"),
                    resource.getInt("IsCodeRest")));
            if (listResponse.stream()
                    .max(Comparator.comparing(DebtChart::getCodeRest))
                    .orElseThrow(NoSuchElementException::new).getCodeRest() == 1) {
                String mCode = "";
                for (DebtChart response : listResponse) {
                    if (response.getCodeRest() == 0) {
                        if (mCode != "") {
                            mCode = mCode + "," + response.getCode();
                        } else {
                            mCode = response.getCode();
                        }
                    }
                }
                List<DebtChart> listReturn = new ArrayList<DebtChart>();
                for (DebtChart response : listResponse) {
                    if (response.getCodeRest() == 1) {
                        response.setCode(mCode);
                    }
                    listReturn.add(response);
                }
                return listReturn;
            } else {
                return listResponse;
            }
        } catch (Exception e) {
            throw new ResourceException(e.getMessage());
        }
    }

    @Override
    public List<DebtChartDetail> getListPayChartDetail(BodyParameterFirst param) {
        try {
            // Check error field
            if (!CheckDateFrom(param.getDateFrom()) || !CheckDateTo(param.getDateTo())
                    || !CheckCode(param.getCode()) || !CheckCodeRest(param.getCodeRest())) {
                throw new ResourceException(
                        ResourceValid.StringError(ResourceValid.typeERROR.FIELD, ""));
            }
            String sql = "EXEC sp_GETTBL_ForAndroid_CongNoTra_CT "
                    + mDateFrom + ", " + mDateTo + ", " + mCode + ", " + mCodeRest + "";
            return jdbcTemplate.query(sql, (resource, rowNum) -> new DebtChartDetail(
                    resource.getString("MaDoiTuong"),
                    resource.getString("TenDoiTuong"),
                    resource.getDate("NgayCTu"),
                    ResourceValid.FormatDouble(resource.getDouble("SoDuDK")),
                    ResourceValid.FormatDouble(resource.getDouble("PhatSinhNo")),
                    ResourceValid.FormatDouble(resource.getDouble("ThanhToan")),
                    ResourceValid.FormatDouble(resource.getDouble("SoDuCK")),
                    resource.getInt("Loai"),
                    resource.getInt("Loai2")));
        } catch (Exception e) {
            throw new ResourceException(e.getMessage());
        }
    }

    @Override
    public List<DebtChart> getListPayChartWithDetail(BodyParameterFirst param) {
        try {
            List<DebtChart> listResponse = getListPayChart(param);
            for (DebtChart response : listResponse) {
                BodyParameterFirst paramDetail = new BodyParameterFirst(
                        "1900/01/01",
                        param.getDateTo(),
                        response.getCode(),
                        response.getCodeRest());
                response.setDetail(getListPayChartDetail(paramDetail));
            }
            return listResponse;
        } catch (Exception e) {
            throw new ResourceException(e.getMessage());
        }
    }

    // Code map
    @Override
    public List<DebtMap> getListMapClientNegative(BodyParameterSecond param) {
        try {
            // Check error field
            if (!CheckDateTo(param.getDateTo()) || !CheckCodeType(param.getCodeType())) {
                throw new ResourceException(
                        ResourceValid.StringError(ResourceValid.typeERROR.FIELD, ""));
            }
            String sql = "EXEC sp_GETTBL_ForAndroid_ByCodeMap_CongNo_KhachHang_Am "
                    + mDateTo + ", " + mCodeType + "";
            return jdbcTemplate.query(sql, (resource, rowNum) -> new DebtMap(
                    resource.getInt("SapXep"),
                    resource.getString("CodeValue"),
                    resource.getString("ThongTinNhom"),
                    ResourceValid.FormatDouble(resource.getDouble("SoTien")),
                    "KHACHHANG"));
        } catch (Exception e) {
            throw new ResourceException(e.getMessage());
        }
    }

    @Override
    public List<DebtMapDetail> getListMapClientNegativeDetail(BodyParameterSecond param) {
        try {
            // Check error field
            if (!CheckDateTo(param.getDateTo()) || !CheckCodeType(param.getCodeType())
                    || !CheckCodeValue(param.getCodeValue())) {
                throw new ResourceException(
                        ResourceValid.StringError(ResourceValid.typeERROR.FIELD, ""));
            }
            String sql = "EXEC sp_GETTBL_ForAndroid_ByCodeMap_CongNo_KhachHang_Am_CT "
                    + mDateTo + ", " + mCodeType + ", " + mCodeValue + "";
            return jdbcTemplate.query(sql, (resource, rowNum) -> new DebtMapDetail(
                    resource.getInt("SapXep"),
                    resource.getString("ThongTinDoiTuong"),
                    ResourceValid.FormatDouble(resource.getDouble("SoTien"))));
        } catch (Exception e) {
            throw new ResourceException(e.getMessage());
        }
    }

    @Override
    public List<DebtMap> getListMapClientNegativeWithDetail(BodyParameterSecond param) {
        try {
            List<DebtMap> listResponse = getListMapClientNegative(param);
            for (DebtMap response : listResponse) {
                BodyParameterSecond paramDetail = new BodyParameterSecond(
                        param.getDateTo(),
                        response.getCodeType(),
                        response.getCode());
                response.setDetail(getListMapClientNegativeDetail(paramDetail));
            }
            return listResponse;
        } catch (Exception e) {
            throw new ResourceException(e.getMessage());
        }
    }

    @Override
    public List<DebtMap> getListMapClientPositive(BodyParameterSecond param) {
        try {
            // Check error field
            if (!CheckDateTo(param.getDateTo()) || !CheckCodeType(param.getCodeType())) {
                throw new ResourceException(
                        ResourceValid.StringError(ResourceValid.typeERROR.FIELD, ""));
            }
            String sql = "EXEC sp_GETTBL_ForAndroid_ByCodeMap_CongNo_KhachHang_Duong "
                    + mDateTo + ", " + mCodeType + "";
            return jdbcTemplate.query(sql, (resource, rowNum) -> new DebtMap(
                    resource.getInt("SapXep"),
                    resource.getString("CodeValue"),
                    resource.getString("ThongTinNhom"),
                    ResourceValid.FormatDouble(resource.getDouble("SoTien")),
                    "KHACHHANG"));
        } catch (Exception e) {
            throw new ResourceException(e.getMessage());
        }
    }

    @Override
    public List<DebtMapDetail> getListMapClientPositiveDetail(BodyParameterSecond param) {
        try {
            // Check error field
            if (!CheckDateTo(param.getDateTo()) || !CheckCodeType(param.getCodeType())
                    || !CheckCodeValue(param.getCodeValue())) {
                throw new ResourceException(
                        ResourceValid.StringError(ResourceValid.typeERROR.FIELD, ""));
            }
            String sql = "EXEC sp_GETTBL_ForAndroid_ByCodeMap_CongNo_KhachHang_Duong_CT "
                    + mDateTo + ", " + mCodeType + ", " + mCodeValue + "";
            return jdbcTemplate.query(sql, (resource, rowNum) -> new DebtMapDetail(
                    resource.getInt("SapXep"),
                    resource.getString("ThongTinDoiTuong"),
                    ResourceValid.FormatDouble(resource.getDouble("SoTien"))));
        } catch (Exception e) {
            throw new ResourceException(e.getMessage());
        }
    }

    @Override
    public List<DebtMap> getListMapClientPositiveWithDetail(BodyParameterSecond param) {
        try {
            List<DebtMap> listResponse = getListMapClientPositive(param);
            for (DebtMap response : listResponse) {
                BodyParameterSecond paramDetail = new BodyParameterSecond(
                        param.getDateTo(),
                        response.getCodeType(),
                        response.getCode());
                response.setDetail(getListMapClientPositiveDetail(paramDetail));
            }
            return listResponse;
        } catch (Exception e) {
            throw new ResourceException(e.getMessage());
        }
    }

    @Override
    public List<DebtMap> getListMapSupplierNegative(BodyParameterSecond param) {
        try {
            // Check error field
            if (!CheckDateTo(param.getDateTo()) || !CheckCodeType(param.getCodeType())) {
                throw new ResourceException(
                        ResourceValid.StringError(ResourceValid.typeERROR.FIELD, ""));
            }
            String sql = "EXEC sp_GETTBL_ForAndroid_ByCodeMap_CongNo_NhaCungCap_Am "
                    + mDateTo + ", " + mCodeType + "";
            return jdbcTemplate.query(sql, (resource, rowNum) -> new DebtMap(
                    resource.getInt("SapXep"),
                    resource.getString("CodeValue"),
                    resource.getString("ThongTinNhom"),
                    ResourceValid.FormatDouble(resource.getDouble("SoTien")),
                    "NHACUNGCAP"));
        } catch (Exception e) {
            throw new ResourceException(e.getMessage());
        }
    }

    @Override
    public List<DebtMapDetail> getListMapSupplierNegativeDetail(BodyParameterSecond param) {
        try {
            // Check error field
            if (!CheckDateTo(param.getDateTo()) || !CheckCodeType(param.getCodeType())
                    || !CheckCodeValue(param.getCodeValue())) {
                throw new ResourceException(
                        ResourceValid.StringError(ResourceValid.typeERROR.FIELD, ""));
            }
            String sql = "EXEC sp_GETTBL_ForAndroid_ByCodeMap_CongNo_NhaCungCap_Am_CT "
                    + mDateTo + ", " + mCodeType + ", " + mCodeValue + "";
            return jdbcTemplate.query(sql, (resource, rowNum) -> new DebtMapDetail(
                    resource.getInt("SapXep"),
                    resource.getString("ThongTinDoiTuong"),
                    ResourceValid.FormatDouble(resource.getDouble("SoTien"))));
        } catch (Exception e) {
            throw new ResourceException(e.getMessage());
        }
    }

    @Override
    public List<DebtMap> getListMapSupplierNegativeWithDetail(BodyParameterSecond param) {
        try {
            List<DebtMap> listResponse = getListMapSupplierNegative(param);
            for (DebtMap response : listResponse) {
                BodyParameterSecond paramDetail = new BodyParameterSecond(
                        param.getDateTo(),
                        response.getCodeType(),
                        response.getCode());
                response.setDetail(getListMapSupplierNegativeDetail(paramDetail));
            }
            return listResponse;
        } catch (Exception e) {
            throw new ResourceException(e.getMessage());
        }
    }

    @Override
    public List<DebtMap> getListMapSupplierPositive(BodyParameterSecond param) {
        try {
            // Check error field
            if (!CheckDateTo(param.getDateTo()) || !CheckCodeType(param.getCodeType())) {
                throw new ResourceException(
                        ResourceValid.StringError(ResourceValid.typeERROR.FIELD, ""));
            }
            String sql = "EXEC sp_GETTBL_ForAndroid_ByCodeMap_CongNo_NhaCungCap_Duong "
                    + mDateTo + ", " + mCodeType + "";
            return jdbcTemplate.query(sql, (resource, rowNum) -> new DebtMap(
                    resource.getInt("SapXep"),
                    resource.getString("CodeValue"),
                    resource.getString("ThongTinNhom"),
                    ResourceValid.FormatDouble(resource.getDouble("SoTien")),
                    "NHACUNGCAP"));
        } catch (Exception e) {
            throw new ResourceException(e.getMessage());
        }
    }

    @Override
    public List<DebtMapDetail> getListMapSupplierPositiveDetail(BodyParameterSecond param) {
        try {
            // Check error field
            if (!CheckDateTo(param.getDateTo()) || !CheckCodeType(param.getCodeType())
                    || !CheckCodeValue(param.getCodeValue())) {
                throw new ResourceException(
                        ResourceValid.StringError(ResourceValid.typeERROR.FIELD, ""));
            }
            String sql = "EXEC sp_GETTBL_ForAndroid_ByCodeMap_CongNo_NhaCungCap_Duong_CT "
                    + mDateTo + ", " + mCodeType + ", " + mCodeValue + "";
            return jdbcTemplate.query(sql, (resource, rowNum) -> new DebtMapDetail(
                    resource.getInt("SapXep"),
                    resource.getString("ThongTinDoiTuong"),
                    ResourceValid.FormatDouble(resource.getDouble("SoTien"))));
        } catch (Exception e) {
            throw new ResourceException(e.getMessage());
        }
    }

    @Override
    public List<DebtMap> getListMapSupplierPositiveWithDetail(BodyParameterSecond param) {
        try {
            List<DebtMap> listResponse = getListMapSupplierPositive(param);
            for (DebtMap response : listResponse) {
                BodyParameterSecond paramDetail = new BodyParameterSecond(
                        param.getDateTo(),
                        response.getCodeType(),
                        response.getCode());
                response.setDetail(getListMapSupplierPositiveDetail(paramDetail));
            }
            return listResponse;
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

    private boolean CheckCode(String code) {
        try {
            if (ResourceValid.TypeIsError(ResourceValid.typeOBJECT.STRING, code)) {
                return false;
            }
            if (ResourceValid.StrIsError(code)) {
                mCode = "NULL";
            } else {
                mCode = "'" + code + "'";
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private boolean CheckCodeValue(String codeValue) {
        try {
            if (ResourceValid.TypeIsError(ResourceValid.typeOBJECT.STRING, codeValue)) {
                return false;
            }
            if (ResourceValid.StrIsError(codeValue)) {
                mCodeValue = "NULL";
            } else {
                mCodeValue = "'" + codeValue + "'";
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private boolean CheckCodeType(String codeType) {
        try {
            if (ResourceValid.TypeIsError(ResourceValid.typeOBJECT.STRING, codeType)) {
                return false;
            }
            if (ResourceValid.StrIsError(codeType)) {
                mCodeType = "NULL";
            } else {
                mCodeType = "'" + codeType + "'";
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private boolean CheckCodeRest(int codeRest) {
        try {
            if (ResourceValid.TypeIsError(ResourceValid.typeOBJECT.INTEGER, codeRest)) {
                return false;
            }
            if (ResourceValid.StrIsError(String.valueOf(codeRest))) {
                mCodeRest = 0;
            } else {
                mCodeRest = codeRest;
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
