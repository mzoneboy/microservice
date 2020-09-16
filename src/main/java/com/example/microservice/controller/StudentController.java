package com.example.microservice.controller;

import com.alibaba.fastjson.JSON;
import com.example.microservice.Entity.Student;
import com.example.microservice.dao.StudentDao;
import com.example.microservice.intf.CacheService;
import com.github.pagehelper.PageInfo;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
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

    @Autowired
    private StudentDao studentDao;

    @Autowired
    private CacheService cacheService;

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
        return String.format("add %s success", name);
    }

    @RequestMapping("addByTk")
    public String addByTk(@RequestParam Map<String, Object> params) {
        String name = String.valueOf(params.get("name"));
        Integer age = Integer.parseInt(String.valueOf(params.get("age")));
        Student student = new Student();
        student.setName(name);
        student.setAge(age);
        studentDao.insert(student);
        return String.format("add %s success", student.getName());
    }

    @RequestMapping("listByTk")
    public String queryByTk() {
        List<Student> list = studentDao.queryList();
        return JSON.toJSONString(list);
    }

    @RequestMapping("pageByTk")
    public String queryPageByTk(@RequestParam int pageNo, @RequestParam int pageSize) {
        PageInfo<Student> pageInfo = studentDao.queryPage(pageNo, pageSize);
        return JSON.toJSONString(pageInfo);
    }

    @RequestMapping("queryById")
    public String queryById(@RequestParam int id) {
        return cacheService.queryFromCache(id);
    }
}
