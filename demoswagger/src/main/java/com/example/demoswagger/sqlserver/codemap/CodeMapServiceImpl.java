package com.example.demoswagger.sqlserver.codemap;

import com.example.demoswagger.module.ResourceException;
import com.example.demoswagger.module.ResourceValid;
import com.example.demoswagger.sqlserver.BodyParameterSecond;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CodeMapServiceImpl implements CodeMapService {

    private String mCodeType;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<CodeMap> getListCodeMap() {
        try {
            String sql = "sp_GETTBL_ForAndroid_CodeMappingList ";
            return jdbcTemplate.query(sql, (resource, rowNum) -> new CodeMap(
                    resource.getInt("CodeOrder"),
                    resource.getString("CodeType"),
                    ResourceValid.FormatDouble(resource.getDouble("CountDetail"))));
        } catch (Exception e) {
            throw new ResourceException(e.getMessage());
        }
    }

    @Override
    public List<CodeMapDetail> getListCodeMapDetail(BodyParameterSecond param) {
        try {
            // Check error field
            if (!CheckCodeType(param.getCodeType())) {
                throw new ResourceException(
                        ResourceValid.StringError(ResourceValid.typeERROR.FIELD, "CodeType"));
            }
            String sql = "sp_GETTBL_ForAndroid_CodeMappingList_Detail " + mCodeType + "";
            return jdbcTemplate.query(sql, (resource, rowNum) -> new CodeMapDetail(
                    resource.getInt("CodeOrder"),
                    resource.getString("CodeType"),
                    resource.getString("CodeValue"),
                    resource.getString("CodeDescription"),
                    resource.getInt("CodeRest"),
                    ResourceValid.FormatDouble(resource.getDouble("CountDetail"))));
        } catch (Exception e) {
            throw new ResourceException(e.getMessage());
        }
    }

    @Override
    public List<CodeMap> getListCodeMapWithDetail() {
        try {
            List<CodeMap> listResponse = getListCodeMap();
            for (CodeMap response : listResponse) {
                BodyParameterSecond paramDetail = new BodyParameterSecond(
                        response.getCodeType());
                response.setDetail(getListCodeMapDetail(paramDetail));
            }
            return listResponse;
        } catch (Exception e) {
            throw new ResourceException(e.getMessage());
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
}
