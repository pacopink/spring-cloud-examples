package com.neo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;

@RestController
public class HelloController {

    @Autowired
    private NamedParameterJdbcTemplate postgresJdbcTemplate;
	
    @RequestMapping("/hello")
    public String index(@RequestParam String name) {
        return "hello "+name+"，this is first messge";
    }

    @RequestMapping("/info")
    public String info() {
        return "this is spring-cloud-producer";
    }

    @RequestMapping("/test")
    public List<String>  test() {
        return postgresJdbcTemplate.queryForList("select name from test", new TreeMap<String, String>(), String.class);
    }

    @RequestMapping("/test2")
    public List<String>  test2() {
        return postgresJdbcTemplate.queryForList("select name2 from test", new TreeMap<String, String>(), String.class);
    }
}