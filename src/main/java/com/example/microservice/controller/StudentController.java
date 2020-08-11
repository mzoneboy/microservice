package com.example.microservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RequestMapping("stu")
@RestController
public class StudentController {
    private static final Logger logger = LoggerFactory.getLogger(StudentController.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @RequestMapping("list")
    public List<Map<String, Object>> queryList() {
        List<Map<String, Object>> list = jdbcTemplate.queryForList("SELECT id,name FROM test_db.student ", new Object[]{});
        return list;
    }

    @RequestMapping("del")
    public String deleteById(@RequestParam String name) {
         jdbcTemplate.execute("delete FROM test_db.student where name='"+name+"'");
         return String.format("delete %s success", name);
    }

    @RequestMapping("add")
    public String add(@RequestParam Map<String, Object> params) {
        String name = String.valueOf(params.get("name"));
        Integer age = Integer.parseInt(String.valueOf(params.get("age")));
        jdbcTemplate.execute("insert into student (name,age) values ('"+name+"'"+","+age+")");
        return String.format("delete %s success", name);
    }
}