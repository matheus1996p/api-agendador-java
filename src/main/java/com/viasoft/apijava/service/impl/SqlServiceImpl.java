package com.viasoft.apijava.service.impl;

import org.codehaus.jackson.map.ObjectMapper;
import com.viasoft.apijava.model.mapper.SqlMapper;
import com.viasoft.apijava.service.SqlService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class SqlServiceImpl implements SqlService {

    private ResourceLoader resourceLoader;
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public SqlServiceImpl(
            @Qualifier("webApplicationContext") ResourceLoader resourceLoader,
            NamedParameterJdbcTemplate jdbcTemplate
    ) {
        this.resourceLoader = resourceLoader;
        this.jdbcTemplate = jdbcTemplate;
        Locale.setDefault(new Locale("pt", "BR"));
    }


    @Override
    public List getSql(String pack, String filename, Map<String, String> params) {
        String sql = getSqlFromFile(pack, filename);
        return getSqlResponse(sql, params);
    }

    private String getSqlFromFile(String pack, String filename) {
        try {
            Resource resource = resourceLoader.getResource("classpath:/sql/"+ pack + "/" + filename + ".sql");
            InputStream sqlStream = resource.getInputStream();
            return IOUtils.toString(sqlStream, "ISO8859_1");
        } catch (IOException e) {
             e.printStackTrace();
            return null;
        }
    }

    private List getSqlResponse(String sql, Map<String, String> params) {
        List<String> sqlParams = getParameters(sql);
        Map formattedParams = formatParams(params);
        sqlParams = getEmptyParams(sqlParams, formattedParams);

        for (String param : sqlParams) {
            formattedParams.put(param, 0);
        }

        ObjectMapper objectMapper = new ObjectMapper();
        return jdbcTemplate.query(sql, formattedParams, new SqlMapper(objectMapper));
    }

    private List<String> getParameters(String sql) {
        Pattern pattern = Pattern.compile("([:])\\w+");
        List<String> allMatches = new ArrayList<String>();
        Set<String> matchesSet = new HashSet<>();
        Matcher matcher = pattern.matcher(sql);

        while (matcher.find()) {
            allMatches.add(matcher.group().split(":")[1]);
        }

        matchesSet.addAll(allMatches);
        allMatches.clear();
        allMatches.addAll(matchesSet);

        return allMatches;
    }

    private Map formatParams(Map<String, String> params) {
        try {
            Map formattedParams = new HashMap();

            for (Map.Entry<String, String> entry : params.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                Integer number = 0;
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                if (value.contains(",")) {
                    String[] values = value.split(",");
                    try {
                        number = Integer.parseInt(values[0]);
                        if (number > 0) {
                            List valuesList = Arrays.asList(values);
                            formattedParams.put(key, valuesList);
                        } else {
                            formattedParams.put(key, value);
                        }
                    } catch (Exception e) {
                        formattedParams.put(key, value);
                    }
                } else if (key.startsWith("DT")) {
                    try {
                        Date dt = dateFormat.parse(value);
                        formattedParams.put(key, dt);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                } else {
                    formattedParams.put(key, value);
                }
            }
            return formattedParams;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private List<String> getEmptyParams(List<String> sqlParams, Map<String, String> params){
        for (int i = sqlParams.size() - 1; i >= 0; i--) {
            String param = sqlParams.get(i);
            for (Map.Entry<String, String> entry : params.entrySet()) {
                String key  = entry.getKey();
                if (param.equals(key)){
                    sqlParams.remove(param);
                }
            }
        }
        return sqlParams;
    }
}
