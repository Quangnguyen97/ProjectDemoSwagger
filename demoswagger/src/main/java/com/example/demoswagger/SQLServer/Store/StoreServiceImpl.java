package com.example.demoswagger.SQLServer.Store;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.example.demoswagger.Module.*;
import com.example.demoswagger.SQLServer.*;

@Service
public class StoreServiceImpl implements StoreService {

    private String mDateFrom, mDateTo, mUser, mStore;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Retail
    @Override
    public List<StoreRetail> getListStoreRetail() {
        try {
            String sql = "SELECT ROW_NUMBER() OVER(ORDER BY MaKho) as SapXep, MaKho, TenKho, TenDayDu, DiaChi FROM lsttbl_KhoHang ";
            return jdbcTemplate.query(sql, (resource, rowNum) -> new StoreRetail(
                    resource.getInt("SapXep"),
                    resource.getString("MaKho"),
                    resource.getString("TenKho"),
                    resource.getString("TenDayDu"),
                    resource.getString("DiaChi")));
        } catch (Exception e) {
            throw new ResourceException(e.getMessage());
        }
    }

    @Override
    public List<Store> getListRetailImport(BodyParameterSecond param) {
        try {
            // Check error field
            if (!CheckDateFrom(param.getDateFrom()) || !CheckDateTo(param.getDateTo())
                    || !CheckUser(param.getUser()) || !CheckStore(param.getStore())) {
                throw new ResourceException(
                        ResourceValid.StringError(ResourceValid.typeERROR.FIELD, ""));
            }
            String sql = "EXEC sp_GETTBL_ForAndroid_KhoLe_NhapKho "
                    + mUser + "," + mStore + ", " + mDateFrom + ", " + mDateTo + "";
            return jdbcTemplate.query(sql, (resource, rowNum) -> new Store(
                    resource.getInt("SapXep"),
                    resource.getString("ThongTinHangHoa"),
                    ResourceValid.FormatDouble(resource.getDouble("SoLuong"))));
        } catch (Exception e) {
            throw new ResourceException(e.getMessage());
        }
    }

    @Override
    public List<Store> getListRetailInventory(BodyParameterSecond param) {
        try {
            // Check error field
            if (!CheckDateFrom(param.getDateFrom()) || !CheckDateTo(param.getDateTo())
                    || !CheckUser(param.getUser()) || !CheckStore(param.getStore())) {
                throw new ResourceException(
                        ResourceValid.StringError(ResourceValid.typeERROR.FIELD, ""));
            }
            String sql = "EXEC sp_GETTBL_ForAndroid_KhoLe_TonKho "
                    + mUser + "," + mStore + ", " + mDateFrom + ", " + mDateTo + "";
            return jdbcTemplate.query(sql, (resource, rowNum) -> new Store(
                    resource.getInt("SapXep"),
                    resource.getString("ThongTinHangHoa"),
                    ResourceValid.FormatDouble(resource.getDouble("SoLuong"))));
        } catch (Exception e) {
            throw new ResourceException(e.getMessage());
        }
    }

    @Override
    public List<Store> getListRetailExport(BodyParameterSecond param) {
        try {
            // Check error field
            if (!CheckDateFrom(param.getDateFrom()) || !CheckDateTo(param.getDateTo())
                    || !CheckUser(param.getUser()) || !CheckStore(param.getStore())) {
                throw new ResourceException(
                        ResourceValid.StringError(ResourceValid.typeERROR.FIELD, ""));
            }
            String sql = "EXEC sp_GETTBL_ForAndroid_KhoLe_XuatKho "
                    + mUser + "," + mStore + ", " + mDateFrom + ", " + mDateTo + "";
            return jdbcTemplate.query(sql, (resource, rowNum) -> new Store(
                    resource.getInt("SapXep"),
                    resource.getString("ThongTinHangHoa"),
                    ResourceValid.FormatDouble(resource.getDouble("SoLuong"))));
        } catch (Exception e) {
            throw new ResourceException(e.getMessage());
        }
    }

    // Inventory
    @Override
    public List<Store> getListInventory(BodyParameterFirst param) {
        try {
            // Check error field
            if (!CheckDateTo(param.getDateTo())) {
                throw new ResourceException(
                        ResourceValid.StringError(ResourceValid.typeERROR.FIELD, "DateTo"));
            }
            String sql = "EXEC sp_GETTBL_ForAndroid_TonKho " + mDateTo + "";
            return jdbcTemplate.query(sql, (resource, rowNum) -> new Store(
                    resource.getInt("SapXep"),
                    resource.getString("ThongTinHangHoa"),
                    ResourceValid.FormatDouble(resource.getDouble("SoLuong")),
                    resource.getInt("Loai")));
        } catch (Exception e) {
            throw new ResourceException(e.getMessage());
        }
    }

    @Override
    public List<Store> getListInventoryWithStock(BodyParameterFirst param) {
        try {
            // Check error field
            if (!CheckDateTo(param.getDateTo())) {
                throw new ResourceException(
                        ResourceValid.StringError(ResourceValid.typeERROR.FIELD, "DateTo"));
            }
            String sql = "EXEC sp_GETTBL_ForAndroid_TonKho_TheoKho " + mDateTo + "";
            return jdbcTemplate.query(sql, (resource, rowNum) -> new Store(
                    resource.getInt("SapXep"),
                    resource.getString("ThongTinHangHoa"),
                    ResourceValid.FormatDouble(resource.getDouble("SoLuong")),
                    resource.getInt("Loai")));
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

    private boolean CheckUser(String user) {
        try {
            if (ResourceValid.TypeIsError(ResourceValid.typeOBJECT.STRING, user)) {
                return false;
            }
            if (ResourceValid.StrIsError(user)) {
                Authentication auth = SecurityContextHolder.getContext().getAuthentication();
                if (auth == null) {
                    mUser = "NULL";
                } else {
                    mUser = "'" + auth.getPrincipal() + "'";
                }
            } else {
                mUser = "'" + user + "'";
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private boolean CheckStore(String store) {
        try {
            if (ResourceValid.TypeIsError(ResourceValid.typeOBJECT.STRING, store)) {
                return false;
            }
            if (ResourceValid.StrIsError(store)) {
                mStore = "NULL";
            } else {
                mStore = "'" + store + "'";
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
