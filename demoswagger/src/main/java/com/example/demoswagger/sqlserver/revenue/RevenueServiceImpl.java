package com.example.demoswagger.sqlserver.revenue;

import com.example.demoswagger.module.ResourceException;
import com.example.demoswagger.module.ResourceValid;
import com.example.demoswagger.sqlserver.BodyParameterFirst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class RevenueServiceImpl implements RevenueService {

    private String mDateFrom, mDateTo, mCode, mCodeValue, mCodeType;
    private Integer mCodeRest, mType;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Top chart
    @Override
    public List<RevenueChart> getListChartClientNegative(BodyParameterFirst param) {
        try {
            // Check error field
            if (!CheckDateFrom(param.getDateFrom()) || !CheckDateTo(param.getDateTo())) {
                throw new ResourceException(
                        ResourceValid.StringError(ResourceValid.typeERROR.FIELD, ""));
            }
            String sql = "EXEC sp_GETTBL_ForAndroid_TopChart_DoanhSoKhachHang_Am " + mDateFrom + ", " + mDateTo + "";
            List<RevenueChart> listResponse = jdbcTemplate.query(sql, (resource, rowNum) -> new RevenueChart(
                    resource.getInt("SapXep"),
                    resource.getString("MaDoiTuong"),
                    resource.getString("ThongTinDoiTuong"),
                    ResourceValid.FormatDouble(resource.getDouble("SoTien")),
                    resource.getDouble("TyTrong"),
                    resource.getInt("IsCodeRest")));
            if (listResponse.stream()
                    .max(Comparator.comparing(RevenueChart::getCodeRest))
                    .orElseThrow(NoSuchElementException::new).getCodeRest() == 1) {
                String mCode = "";
                for (RevenueChart response : listResponse) {
                    if (response.getCodeRest() == 0) {
                        if (mCode != "") {
                            mCode = mCode + "," + response.getCode();
                        } else {
                            mCode = response.getCode();
                        }
                    }
                }
                List<RevenueChart> listReturn = new ArrayList<RevenueChart>();
                for (RevenueChart response : listResponse) {
                    if (response.getCodeRest() == 1) {
                        response.setCode(mCode);
                        response.setName("Các mã còn lại không thuộc danh sách trên");
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
    public List<RevenueChart> getListChartClientPositive(BodyParameterFirst param) {
        try {
            // Check error field
            if (!CheckDateFrom(param.getDateFrom()) || !CheckDateTo(param.getDateTo())) {
                throw new ResourceException(
                        ResourceValid.StringError(ResourceValid.typeERROR.FIELD, ""));
            }
            String sql = "EXEC sp_GETTBL_ForAndroid_TopChart_DoanhSoKhachHang_Duong " + mDateFrom + ", " + mDateTo + "";
            List<RevenueChart> listResponse = jdbcTemplate.query(sql, (resource, rowNum) -> new RevenueChart(
                    resource.getInt("SapXep"),
                    resource.getString("MaDoiTuong"),
                    resource.getString("ThongTinDoiTuong"),
                    ResourceValid.FormatDouble(resource.getDouble("SoTien")),
                    resource.getDouble("TyTrong"),
                    resource.getInt("IsCodeRest")));
            if (listResponse.stream()
                    .max(Comparator.comparing(RevenueChart::getCodeRest))
                    .orElseThrow(NoSuchElementException::new).getCodeRest() == 1) {
                String mCode = "";
                for (RevenueChart response : listResponse) {
                    if (response.getCodeRest() == 0) {
                        if (mCode != "") {
                            mCode = mCode + "," + response.getCode();
                        } else {
                            mCode = response.getCode();
                        }
                    }
                }
                List<RevenueChart> listReturn = new ArrayList<RevenueChart>();
                for (RevenueChart response : listResponse) {
                    if (response.getCodeRest() == 1) {
                        response.setCode(mCode);
                        response.setName("Các mã còn lại không thuộc danh sách trên");
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
    public List<RevenueChartDetail> getListChartClientDetail(BodyParameterFirst param) {
        try {
            // Check error field
            if (!CheckDateFrom(param.getDateFrom()) || !CheckDateTo(param.getDateTo())
                    || !CheckCode(param.getCode()) || !CheckCodeRest(param.getCodeRest())
                    || !CheckType(param.getType())) {
                throw new ResourceException(
                        ResourceValid.StringError(ResourceValid.typeERROR.FIELD, ""));
            }
            String sql = "EXEC sp_GETTBL_ForAndroid_DoanhSoKhachHang_CT "
                    + mDateFrom + ", " + mDateTo + ", " + mCode + ", " + mCodeRest + ", " + mType + "";
            return jdbcTemplate.query(sql, (resource, rowNum) -> new RevenueChartDetail(
                    resource.getInt("SapXep"),
                    resource.getString("MaDoiTuong"),
                    resource.getString("ThongTinDoiTuong"),
                    resource.getDate("NgayCTu"),
                    ResourceValid.FormatDouble(resource.getDouble("SoTien")),
                    resource.getInt("Loai")));
        } catch (Exception e) {
            throw new ResourceException(e.getMessage());
        }
    }

    @Override
    public List<RevenueChart> getListChartClientNegativeWithDetail(BodyParameterFirst param) {
        try {
            List<RevenueChart> listResponse = getListChartClientNegative(param);
            for (RevenueChart response : listResponse) {
                BodyParameterFirst paramDetail = new BodyParameterFirst(
                        param.getDateFrom(),
                        param.getDateTo(),
                        response.getCode(),
                        response.getCodeRest(),
                        0);
                response.setDetail(getListChartClientDetail(paramDetail));
            }
            return listResponse;
        } catch (Exception e) {
            throw new ResourceException(e.getMessage());
        }
    }

    @Override
    public List<RevenueChart> getListChartClientPositiveWithDetail(BodyParameterFirst param) {
        try {
            List<RevenueChart> listResponse = getListChartClientPositive(param);
            for (RevenueChart response : listResponse) {
                BodyParameterFirst paramDetail = new BodyParameterFirst(
                        param.getDateFrom(),
                        param.getDateTo(),
                        response.getCode(),
                        response.getCodeRest(),
                        1);
                response.setDetail(getListChartClientDetail(paramDetail));
            }
            return listResponse;
        } catch (Exception e) {
            throw new ResourceException(e.getMessage());
        }
    }

    // Code map
    @Override
    public List<RevenueMap> getListMapClientNegative(BodyParameterFirst param) {
        try {
            // Check error field
            if (!CheckDateFrom(param.getDateFrom()) || !CheckDateTo(param.getDateTo())
                    || !CheckCodeType(param.getCodeType())) {
                throw new ResourceException(
                        ResourceValid.StringError(ResourceValid.typeERROR.FIELD, ""));
            }
            String sql = "EXEC sp_GETTBL_ForAndroid_ByCodeMap_DoanhSo_KhachHang_Am "
                    + mDateFrom + ", " + mDateTo + ", " + mCodeType + "";
            return jdbcTemplate.query(sql, (resource, rowNum) -> new RevenueMap(
                    resource.getInt("SapXep"),
                    resource.getString("CodeValue"),
                    resource.getString("ThongTinNhom"),
                    ResourceValid.FormatDouble(resource.getDouble("SoTien")),
                    param.getCodeType()));
        } catch (Exception e) {
            throw new ResourceException(e.getMessage());
        }
    }

    @Override
    public List<RevenueMapDetail> getListMapClientNegativeDetail(BodyParameterFirst param) {
        try {
            // Check error field
            if (!CheckDateFrom(param.getDateFrom()) || !CheckDateTo(param.getDateTo())
                    || !CheckCodeType(param.getCodeType()) || !CheckCodeValue(param.getCodeValue())) {
                throw new ResourceException(
                        ResourceValid.StringError(ResourceValid.typeERROR.FIELD, ""));
            }
            String sql = "EXEC sp_GETTBL_ForAndroid_ByCodeMap_DoanhSo_KhachHang_Am_CT "
                    + mDateFrom + "," + mDateTo + ", " + mCodeType + ", " + mCodeValue + "";
            return jdbcTemplate.query(sql, (resource, rowNum) -> new RevenueMapDetail(
                    resource.getInt("SapXep"),
                    resource.getString("ThongTinDoiTuong"),
                    ResourceValid.FormatDouble(resource.getDouble("SoTien"))));
        } catch (Exception e) {
            throw new ResourceException(e.getMessage());
        }
    }

    @Override
    public List<RevenueMap> getListMapClientNegativeWithDetail(BodyParameterFirst param) {
        try {
            List<RevenueMap> listResponse = getListMapClientNegative(param);
            for (RevenueMap response : listResponse) {
                BodyParameterFirst paramDetail = new BodyParameterFirst(
                        param.getDateFrom(),
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
    public List<RevenueMap> getListMapClientPositive(BodyParameterFirst param) {
        try {
            // Check error field
            if (!CheckDateFrom(param.getDateFrom()) || !CheckDateTo(param.getDateTo())
                    || !CheckCodeType(param.getCodeType())) {
                throw new ResourceException(
                        ResourceValid.StringError(ResourceValid.typeERROR.FIELD, ""));
            }
            String sql = "EXEC sp_GETTBL_ForAndroid_ByCodeMap_DoanhSo_KhachHang_Duong "
                    + mDateFrom + ", " + mDateTo + ", " + mCodeType + "";
            return jdbcTemplate.query(sql, (resource, rowNum) -> new RevenueMap(
                    resource.getInt("SapXep"),
                    resource.getString("CodeValue"),
                    resource.getString("ThongTinNhom"),
                    ResourceValid.FormatDouble(resource.getDouble("SoTien")),
                    param.getCodeType()));
        } catch (Exception e) {
            throw new ResourceException(e.getMessage());
        }
    }

    @Override
    public List<RevenueMapDetail> getListMapClientPositiveDetail(BodyParameterFirst param) {
        try {
            // Check error field
            if (!CheckDateFrom(param.getDateFrom()) || !CheckDateTo(param.getDateTo())
                    || !CheckCodeType(param.getCodeType()) || !CheckCodeValue(param.getCodeValue())) {
                throw new ResourceException(
                        ResourceValid.StringError(ResourceValid.typeERROR.FIELD, ""));
            }
            String sql = "EXEC sp_GETTBL_ForAndroid_ByCodeMap_DoanhSo_KhachHang_Duong_CT "
                    + mDateFrom + "," + mDateTo + ", " + mCodeType + ", " + mCodeValue + "";
            return jdbcTemplate.query(sql, (resource, rowNum) -> new RevenueMapDetail(
                    resource.getInt("SapXep"),
                    resource.getString("ThongTinDoiTuong"),
                    ResourceValid.FormatDouble(resource.getDouble("SoTien"))));
        } catch (Exception e) {
            throw new ResourceException(e.getMessage());
        }
    }

    @Override
    public List<RevenueMap> getListMapClientPositiveWithDetail(BodyParameterFirst param) {
        try {
            List<RevenueMap> listResponse = getListMapClientPositive(param);
            for (RevenueMap response : listResponse) {
                BodyParameterFirst paramDetail = new BodyParameterFirst(
                        param.getDateFrom(),
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
    public List<RevenueMap> getListMapCommodityNegative(BodyParameterFirst param) {
        try {
            // Check error field
            if (!CheckDateFrom(param.getDateFrom()) || !CheckDateTo(param.getDateTo())
                    || !CheckCodeType(param.getCodeType())) {
                throw new ResourceException(
                        ResourceValid.StringError(ResourceValid.typeERROR.FIELD, ""));
            }
            String sql = "EXEC sp_GETTBL_ForAndroid_ByCodeMap_DoanhSo_HangHoa_Am "
                    + mDateFrom + ", " + mDateTo + ", " + mCodeType + "";
            return jdbcTemplate.query(sql, (resource, rowNum) -> new RevenueMap(
                    resource.getInt("SapXep"),
                    resource.getString("CodeValue"),
                    resource.getString("ThongTinNhom"),
                    ResourceValid.FormatDouble(resource.getDouble("SoTien")),
                    param.getCodeType()));
        } catch (Exception e) {
            throw new ResourceException(e.getMessage());
        }
    }

    @Override
    public List<RevenueMapDetail> getListMapCommodityNegativeDetail(BodyParameterFirst param) {
        try {
            // Check error field
            if (!CheckDateFrom(param.getDateFrom()) || !CheckDateTo(param.getDateTo())
                    || !CheckCodeType(param.getCodeType()) || !CheckCodeValue(param.getCodeValue())) {
                throw new ResourceException(
                        ResourceValid.StringError(ResourceValid.typeERROR.FIELD, ""));
            }
            String sql = "EXEC sp_GETTBL_ForAndroid_ByCodeMap_DoanhSo_HangHoa_Am_CT "
                    + mDateFrom + "," + mDateTo + ", " + mCodeType + ", " + mCodeValue + "";
            return jdbcTemplate.query(sql, (resource, rowNum) -> new RevenueMapDetail(
                    resource.getInt("SapXep"),
                    resource.getString("ThongTinHangHoa"),
                    ResourceValid.FormatDouble(resource.getDouble("SoTien"))));
        } catch (Exception e) {
            throw new ResourceException(e.getMessage());
        }
    }

    @Override
    public List<RevenueMap> getListMapCommodityNegativeWithDetail(BodyParameterFirst param) {
        try {
            List<RevenueMap> listResponse = getListMapCommodityNegative(param);
            for (RevenueMap response : listResponse) {
                BodyParameterFirst paramDetail = new BodyParameterFirst(
                        param.getDateFrom(),
                        param.getDateTo(),
                        response.getCodeType(),
                        response.getCode());
                response.setDetail(getListMapCommodityNegativeDetail(paramDetail));
            }
            return listResponse;
        } catch (Exception e) {
            throw new ResourceException(e.getMessage());
        }
    }

    @Override
    public List<RevenueMap> getListMapCommodityPositive(BodyParameterFirst param) {
        try {
            // Check error field
            if (!CheckDateFrom(param.getDateFrom()) || !CheckDateTo(param.getDateTo())
                    || !CheckCodeType(param.getCodeType())) {
                throw new ResourceException(
                        ResourceValid.StringError(ResourceValid.typeERROR.FIELD, ""));
            }
            String sql = "EXEC sp_GETTBL_ForAndroid_ByCodeMap_DoanhSo_HangHoa_Duong "
                    + mDateFrom + ", " + mDateTo + ", " + mCodeType + "";
            return jdbcTemplate.query(sql, (resource, rowNum) -> new RevenueMap(
                    resource.getInt("SapXep"),
                    resource.getString("CodeValue"),
                    resource.getString("ThongTinNhom"),
                    ResourceValid.FormatDouble(resource.getDouble("SoTien")),
                    param.getCodeType()));
        } catch (Exception e) {
            throw new ResourceException(e.getMessage());
        }
    }

    @Override
    public List<RevenueMapDetail> getListMapCommodityPositiveDetail(BodyParameterFirst param) {
        try {
            // Check error field
            if (!CheckDateFrom(param.getDateFrom()) || !CheckDateTo(param.getDateTo())
                    || !CheckCodeType(param.getCodeType()) || !CheckCodeValue(param.getCodeValue())) {
                throw new ResourceException(
                        ResourceValid.StringError(ResourceValid.typeERROR.FIELD, ""));
            }
            String sql = "EXEC sp_GETTBL_ForAndroid_ByCodeMap_DoanhSo_HangHoa_Duong_CT "
                    + mDateFrom + "," + mDateTo + ", " + mCodeType + ", " + mCodeValue + "";
            return jdbcTemplate.query(sql, (resource, rowNum) -> new RevenueMapDetail(
                    resource.getInt("SapXep"),
                    resource.getString("ThongTinHangHoa"),
                    ResourceValid.FormatDouble(resource.getDouble("SoTien"))));
        } catch (Exception e) {
            throw new ResourceException(e.getMessage());
        }
    }

    @Override
    public List<RevenueMap> getListMapCommodityPositiveWithDetail(BodyParameterFirst param) {
        try {
            List<RevenueMap> listResponse = getListMapCommodityPositive(param);
            for (RevenueMap response : listResponse) {
                BodyParameterFirst paramDetail = new BodyParameterFirst(
                        param.getDateFrom(),
                        param.getDateTo(),
                        response.getCodeType(),
                        response.getCode());
                response.setDetail(getListMapCommodityPositiveDetail(paramDetail));
            }
            return listResponse;
        } catch (Exception e) {
            throw new ResourceException(e.getMessage());
        }
    }

    private boolean CheckDateFrom(String dateFrom) {
        try {
            if (ResourceValid.typeIsError(ResourceValid.typeOBJECT.STRING, dateFrom)) {
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
            if (ResourceValid.typeIsError(ResourceValid.typeOBJECT.STRING, dateTo)) {
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
            if (ResourceValid.typeIsError(ResourceValid.typeOBJECT.STRING, code)) {
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
            if (ResourceValid.typeIsError(ResourceValid.typeOBJECT.STRING, codeValue)) {
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
            if (ResourceValid.typeIsError(ResourceValid.typeOBJECT.STRING, codeType)) {
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
            if (ResourceValid.typeIsError(ResourceValid.typeOBJECT.INTEGER, codeRest)) {
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

    private boolean CheckType(int type) {
        try {
            if (ResourceValid.typeIsError(ResourceValid.typeOBJECT.INTEGER, type)) {
                return false;
            }
            if (ResourceValid.StrIsError(String.valueOf(type))) {
                mType = 0;
            } else {
                mType = type;
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
