package com.example.demoswagger.sqlserver.commodity;

import com.example.demoswagger.module.ResourceException;
import com.example.demoswagger.module.ResourceValid;
import com.example.demoswagger.sqlserver.BodyParameterFirst;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommodityServiceImpl implements CommodityService {

    private String mDateFrom;
    private String mDateTo;

    private final JdbcTemplate jdbcTemplate;

    public CommodityServiceImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final String DATE_FROM_DATE_TO = "DateFrom | DateTo";
    private static final String SAP_XEP = "SapXep";
    private static final String THONG_TIN_HANG_HOA = "ThongTinHangHoa";
    private static final String SO_LUONG = "SoLuong";
    private static final String SO_TIEN = "SoTien";

    // Commod
    @Override
    public List<Commodity> getListSellCommod(BodyParameterFirst param) {
        try {
            // Check error field
            if (checkDateFromDateTo(param)) {
                throw new ResourceException(
                        ResourceValid.StringError(ResourceValid.typeERROR.FIELD, DATE_FROM_DATE_TO));
            }
            String sql = "EXEC sp_GETTBL_ForAndroid_BanHang_HangHoa " + mDateFrom + ", " + mDateTo;
            return jdbcTemplate.query(sql, (resource, rowNum) -> new Commodity(
                    resource.getInt(SAP_XEP),
                    resource.getString(THONG_TIN_HANG_HOA),
                    ResourceValid.FormatDouble(resource.getDouble(SO_LUONG)),
                    ResourceValid.FormatDouble(resource.getDouble(SO_TIEN))));
        } catch (Exception e) {
            throw new ResourceException(e.getMessage());
        }
    }

    @Override
    public List<Commodity> getListBuyCommod(BodyParameterFirst param) {
        try {
            // Check error field
            if (checkDateFromDateTo(param)) {
                throw new ResourceException(
                        ResourceValid.StringError(ResourceValid.typeERROR.FIELD, DATE_FROM_DATE_TO));
            }
            String sql = "EXEC sp_GETTBL_ForAndroid_MuaHang_HangHoa " + mDateFrom + ", " + mDateTo;
            return jdbcTemplate.query(sql, (resource, rowNum) -> new Commodity(
                    resource.getInt(SAP_XEP),
                    resource.getString(THONG_TIN_HANG_HOA),
                    ResourceValid.FormatDouble(resource.getDouble(SO_LUONG)),
                    ResourceValid.FormatDouble(resource.getDouble(SO_TIEN))));
        } catch (Exception e) {
            throw new ResourceException(e.getMessage());
        }
    }

    @Override
    public List<Commodity> getListImportCommod(BodyParameterFirst param) {
        try {
            // Check error field
            if (checkDateFromDateTo(param)) {
                throw new ResourceException(
                        ResourceValid.StringError(ResourceValid.typeERROR.FIELD, DATE_FROM_DATE_TO));
            }
            String sql = "EXEC sp_GETTBL_ForAndroid_NhapTra_HangHoa " + mDateFrom + ", " + mDateTo;
            return jdbcTemplate.query(sql, (resource, rowNum) -> new Commodity(
                    resource.getInt(SAP_XEP),
                    resource.getString(THONG_TIN_HANG_HOA),
                    ResourceValid.FormatDouble(resource.getDouble(SO_LUONG)),
                    ResourceValid.FormatDouble(resource.getDouble(SO_TIEN))));
        } catch (Exception e) {
            throw new ResourceException(e.getMessage());
        }
    }

    @Override
    public List<Commodity> getListExportCommod(BodyParameterFirst param) {
        try {
            // Check error field
            if (checkDateFromDateTo(param)) {
                throw new ResourceException(
                        ResourceValid.StringError(ResourceValid.typeERROR.FIELD, DATE_FROM_DATE_TO));
            }
            String sql = "EXEC sp_GETTBL_ForAndroid_XuatTra_HangHoa " + mDateFrom + ", " + mDateTo;
            return jdbcTemplate.query(sql, (resource, rowNum) -> new Commodity(
                    resource.getInt(SAP_XEP),
                    resource.getString(THONG_TIN_HANG_HOA),
                    ResourceValid.FormatDouble(resource.getDouble(SO_LUONG)),
                    ResourceValid.FormatDouble(resource.getDouble(SO_TIEN))));
        } catch (Exception e) {
            throw new ResourceException(e.getMessage());
        }
    }

    // Client
    @Override
    public List<Commodity> getListSellClient(BodyParameterFirst param) {
        try {
            // Check error field
            if (checkDateFromDateTo(param)) {
                throw new ResourceException(
                        ResourceValid.StringError(ResourceValid.typeERROR.FIELD, DATE_FROM_DATE_TO));
            }
            String sql = "EXEC sp_GETTBL_ForAndroid_BanHang_KhachHang " + mDateFrom + ", " + mDateTo;
            return jdbcTemplate.query(sql, (resource, rowNum) -> new Commodity(
                    resource.getInt(SAP_XEP),
                    resource.getString(THONG_TIN_HANG_HOA),
                    ResourceValid.FormatDouble(resource.getDouble(SO_LUONG)),
                    ResourceValid.FormatDouble(resource.getDouble(SO_TIEN)),
                    resource.getInt("Loai")));
        } catch (Exception e) {
            throw new ResourceException(e.getMessage());
        }
    }

    @Override
    public List<Commodity> getListImportClient(BodyParameterFirst param) {
        try {
            // Check error field
            if (checkDateFromDateTo(param)) {
                throw new ResourceException(
                        ResourceValid.StringError(ResourceValid.typeERROR.FIELD, DATE_FROM_DATE_TO));
            }
            String sql = "EXEC sp_GETTBL_ForAndroid_NhapTra_KhachHang " + mDateFrom + ", " + mDateTo;
            return jdbcTemplate.query(sql, (resource, rowNum) -> new Commodity(
                    resource.getInt(SAP_XEP),
                    resource.getString(THONG_TIN_HANG_HOA),
                    ResourceValid.FormatDouble(resource.getDouble(SO_LUONG)),
                    ResourceValid.FormatDouble(resource.getDouble(SO_TIEN)),
                    resource.getInt("Loai")));
        } catch (Exception e) {
            throw new ResourceException(e.getMessage());
        }
    }

    // Supplier
    @Override
    public List<Commodity> getListBuySupplier(BodyParameterFirst param) {
        try {
            // Check error field
            if (checkDateFromDateTo(param)) {
                throw new ResourceException(
                        ResourceValid.StringError(ResourceValid.typeERROR.FIELD, DATE_FROM_DATE_TO));
            }
            String sql = "EXEC sp_GETTBL_ForAndroid_MuaHang_NhaCungCap " + mDateFrom + ", " + mDateTo;
            return jdbcTemplate.query(sql, (resource, rowNum) -> new Commodity(
                    resource.getInt(SAP_XEP),
                    resource.getString(THONG_TIN_HANG_HOA),
                    ResourceValid.FormatDouble(resource.getDouble(SO_LUONG)),
                    ResourceValid.FormatDouble(resource.getDouble(SO_TIEN)),
                    resource.getInt("Loai")));
        } catch (Exception e) {
            throw new ResourceException(e.getMessage());
        }
    }

    @Override
    public List<Commodity> getListExportSupplier(BodyParameterFirst param) {
        try {
            // Check error field
            if (checkDateFromDateTo(param)) {
                throw new ResourceException(
                        ResourceValid.StringError(ResourceValid.typeERROR.FIELD, DATE_FROM_DATE_TO));
            }
            String sql = "EXEC sp_GETTBL_ForAndroid_XuatTra_NhaCungCap " + mDateFrom + ", " + mDateTo;
            return jdbcTemplate.query(sql, (resource, rowNum) -> new Commodity(
                    resource.getInt(SAP_XEP),
                    resource.getString(THONG_TIN_HANG_HOA),
                    ResourceValid.FormatDouble(resource.getDouble(SO_LUONG)),
                    ResourceValid.FormatDouble(resource.getDouble(SO_TIEN)),
                    resource.getInt("Loai")));
        } catch (Exception e) {
            throw new ResourceException(e.getMessage());
        }
    }

    // Counter
    @Override
    public List<Commodity> getListSellCounter(BodyParameterFirst param) {
        try {
            // Check error field
            if (checkDateFromDateTo(param)) {
                throw new ResourceException(
                        ResourceValid.StringError(ResourceValid.typeERROR.FIELD, DATE_FROM_DATE_TO));
            }
            String sql = "EXEC sp_GETTBL_ForAndroid_BanHang_QuayLe " + mDateFrom + ", " + mDateTo;
            return jdbcTemplate.query(sql, (resource, rowNum) -> new Commodity(
                    resource.getInt(SAP_XEP),
                    resource.getString(THONG_TIN_HANG_HOA),
                    ResourceValid.FormatDouble(resource.getDouble(SO_LUONG)),
                    ResourceValid.FormatDouble(resource.getDouble(SO_TIEN)),
                    resource.getInt("Loai")));
        } catch (Exception e) {
            throw new ResourceException(e.getMessage());
        }
    }

    private boolean checkDateFromDateTo(BodyParameterFirst param) {
        try {
            if (ResourceValid.typeIsError(ResourceValid.typeOBJECT.STRING, param.getDateFrom())
                    || ResourceValid.typeIsError(ResourceValid.typeOBJECT.STRING, param.getDateTo())) {
                return true;
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
            return false;
        } catch (Exception e) {
            return true;
        }
    }
}
