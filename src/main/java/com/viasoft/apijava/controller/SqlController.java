package com.viasoft.apijava.controller;

import com.viasoft.apijava.service.SqlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
@RequestMapping(value = "/sql")
public class SqlController {

    @Autowired
    private SqlService sqlService;

    @RequestMapping(value = "/{package}/{sql}", method = {RequestMethod.GET, RequestMethod.POST}, produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity sqlGet(@PathVariable("package") String pack, @PathVariable("sql") String sql, @RequestParam Map<String, String> params) {
        return new ResponseEntity(sqlService.getSql(pack, sql, params).toString(), HttpStatus.OK);

    }
}
