package com.viasoft.apijava.service;

import java.util.List;
import java.util.Map;

public interface SqlService {

    public static String ORACLE = "oracle";
    public static String FIREBIRD = "firebird";

    public List getSql(String caminho, String filename, Map<String, String> params);
}
