package com.example.demoswagger.SQLServer.CodeMap;

import java.util.List;

import com.example.demoswagger.SQLServer.*;

public interface CodeMapService {

    List<CodeMap> getListCodeMap();

    List<CodeMapDetail> getListCodeMapDetail(BodyParameterSecond param);

    List<CodeMap> getListCodeMapWithDetail();
}
