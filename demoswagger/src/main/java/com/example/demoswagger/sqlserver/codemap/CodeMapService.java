package com.example.demoswagger.sqlserver.codemap;

import com.example.demoswagger.sqlserver.BodyParameterSecond;

import java.util.List;

public interface CodeMapService {

    List<CodeMap> getListCodeMap();

    List<CodeMapDetail> getListCodeMapDetail(BodyParameterSecond param);

    List<CodeMap> getListCodeMapWithDetail();
}
