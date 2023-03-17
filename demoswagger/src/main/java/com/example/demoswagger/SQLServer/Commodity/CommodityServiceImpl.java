package com.example.demoswagger.SQLServer.Commodity;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.example.demoswagger.Module.*;
import com.example.demoswagger.SQLServer.BodyParameterFirst;

@Service
public class CommodityServiceImpl implements CommodityService {

    private String mDateFrom, mDateTo;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Commod
    @Override
    public List<Commodity> getListSellCommod(BodyParameterFirst param) {
        try {
            // Check error field
            if (!CheckDateFromDateTo(param)) {
                throw new ResourceException(
                        ResourceValid.StringError(ResourceValid.typeERROR.FIELD, "DateFrom | DateTo"));
            }
            String sql = "EXEC sp_GETTBL_ForAndroid_BanHang_HangHoa " + mDateFrom + ", " + mDateTo + "";
            return jdbcTemplate.query(sql, (resource, rowNum) -> new Commodity(
                    resource.getInt("SapXep"),
                    resource.getString("ThongTinHangHoa"),
                    ResourceValid.FormatDouble(resource.getDouble("SoLuong")),
                    ResourceValid.FormatDouble(resource.getDouble("SoTien"))));
        } catch (Exception e) {
            throw new ResourceException(e.getMessage());
        }
    }

    @Override
    public List<Commodity> getListBuyCommod(BodyParameterFirst param) {
        try {
            // Check error field
            if (!CheckDateFromDateTo(param)) {
                throw new ResourceException(
                        ResourceValid.StringError(ResourceValid.typeERROR.FIELD, "DateFrom | DateTo"));
            }
            String sql = "EXEC sp_GETTBL_ForAndroid_MuaHang_HangHoa " + mDateFrom + ", " + mDateTo + "";
            return jdbcTemplate.query(sql, (resource, rowNum) -> new Commodity(
                    resource.getInt("SapXep"),
                    resource.getString("ThongTinHangHoa"),
                    ResourceValid.FormatDouble(resource.getDouble("SoLuong")),
                    ResourceValid.FormatDouble(resource.getDouble("SoTien"))));
        } catch (Exception e) {
            throw new ResourceException(e.getMessage());
        }
    }

    @Override
    public List<Commodity> getListImportCommod(BodyParameterFirst param) {
        try {
            // Check error field
            if (!CheckDateFromDateTo(param)) {
                throw new ResourceException(
                        ResourceValid.StringError(ResourceValid.typeERROR.FIELD, "DateFrom | DateTo"));
            }
            String sql = "EXEC sp_GETTBL_ForAndroid_NhapTra_HangHoa " + mDateFrom + ", " + mDateTo + "";
            return jdbcTemplate.query(sql, (resource, rowNum) -> new Commodity(
                    resource.getInt("SapXep"),
                    resource.getString("ThongTinHangHoa"),
                    ResourceValid.FormatDouble(resource.getDouble("SoLuong")),
                    ResourceValid.FormatDouble(resource.getDouble("SoTien"))));
        } catch (Exception e) {
            throw new ResourceException(e.getMessage());
        }
    }

    @Override
    public List<Commodity> getListExportCommod(BodyParameterFirst param) {
        try {
            // Check error field
            if (!CheckDateFromDateTo(param)) {
                throw new ResourceException(
                        ResourceValid.StringError(ResourceValid.typeERROR.FIELD, "DateFrom | DateTo"));
            }
            String sql = "EXEC sp_GETTBL_ForAndroid_XuatTra_HangHoa " + mDateFrom + ", " + mDateTo + "";
            return jdbcTemplate.query(sql, (resource, rowNum) -> new Commodity(
                    resource.getInt("SapXep"),
                    resource.getString("ThongTinHangHoa"),
                    ResourceValid.FormatDouble(resource.getDouble("SoLuong")),
                    ResourceValid.FormatDouble(resource.getDouble("SoTien"))));
        } catch (Exception e) {
            throw new ResourceException(e.getMessage());
        }
    }

    // Client
    @Override
    public List<Commodity> getListSellClient(BodyParameterFirst param) {
        try {
            // Check error field
            if (!CheckDateFromDateTo(param)) {
                throw new ResourceException(
                        ResourceValid.StringError(ResourceValid.typeERROR.FIELD, "DateFrom | DateTo"));
            }
            String sql = "EXEC sp_GETTBL_ForAndroid_BanHang_KhachHang " + mDateFrom + ", " + mDateTo + "";
            return jdbcTemplate.query(sql, (resource, rowNum) -> new Commodity(
                    resource.getInt("SapXep"),
                    resource.getString("ThongTinHangHoa"),
                    ResourceValid.FormatDouble(resource.getDouble("SoLuong")),
                    ResourceValid.FormatDouble(resource.getDouble("SoTien")),
                    resource.getInt("Loai")));
        } catch (Exception e) {
            throw new ResourceException(e.getMessage());
        }
    }

    @Override
    public List<Commodity> getListImportClient(BodyParameterFirst param) {
        try {
            // Check error field
            if (!CheckDateFromDateTo(param)) {
                throw new ResourceException(
                        ResourceValid.StringError(ResourceValid.typeERROR.FIELD, "DateFrom | DateTo"));
            }
            String sql = "EXEC sp_GETTBL_ForAndroid_NhapTra_KhachHang " + mDateFrom + ", " + mDateTo + "";
            return jdbcTemplate.query(sql, (resource, rowNum) -> new Commodity(
                    resource.getInt("SapXep"),
                    resource.getString("ThongTinHangHoa"),
                    ResourceValid.FormatDouble(resource.getDouble("SoLuong")),
                    ResourceValid.FormatDouble(resource.getDouble("SoTien")),
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
            if (!CheckDateFromDateTo(param)) {
                throw new ResourceException(
                        ResourceValid.StringError(ResourceValid.typeERROR.FIELD, "DateFrom | DateTo"));
            }
            String sql = "EXEC sp_GETTBL_ForAndroid_MuaHang_NhaCungCap " + mDateFrom + ", " + mDateTo + "";
            return jdbcTemplate.query(sql, (resource, rowNum) -> new Commodity(
                    resource.getInt("SapXep"),
                    resource.getString("ThongTinHangHoa"),
                    ResourceValid.FormatDouble(resource.getDouble("SoLuong")),
                    ResourceValid.FormatDouble(resource.getDouble("SoTien")),
                    resource.getInt("Loai")));
        } catch (Exception e) {
            throw new ResourceException(e.getMessage());
        }
    }

    @Override
    public List<Commodity> getListExportSupplier(BodyParameterFirst param) {
        try {
            // Check error field
            if (!CheckDateFromDateTo(param)) {
                throw new ResourceException(
                        ResourceValid.StringError(ResourceValid.typeERROR.FIELD, "DateFrom | DateTo"));
            }
            String sql = "EXEC sp_GETTBL_ForAndroid_XuatTra_NhaCungCap " + mDateFrom + ", " + mDateTo + "";
            return jdbcTemplate.query(sql, (resource, rowNum) -> new Commodity(
                    resource.getInt("SapXep"),
                    resource.getString("ThongTinHangHoa"),
                    ResourceValid.FormatDouble(resource.getDouble("SoLuong")),
                    ResourceValid.FormatDouble(resource.getDouble("SoTien")),
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
            if (!CheckDateFromDateTo(param)) {
                throw new ResourceException(
                        ResourceValid.StringError(ResourceValid.typeERROR.FIELD, "DateFrom | DateTo"));
            }
            String sql = "EXEC sp_GETTBL_ForAndroid_BanHang_QuayLe " + mDateFrom + ", " + mDateTo + "";
            return jdbcTemplate.query(sql, (resource, rowNum) -> new Commodity(
                    resource.getInt("SapXep"),
                    resource.getString("ThongTinHangHoa"),
                    ResourceValid.FormatDouble(resource.getDouble("SoLuong")),
                    ResourceValid.FormatDouble(resource.getDouble("SoTien")),
                    resource.getInt("Loai")));
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
