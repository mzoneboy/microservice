package com.example.microservice.dao;

import com.example.microservice.Entity.Student;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

public interface IStudentDao {
    int insert(Student student);

    List<Student> queryList();

    public PageInfo<Student> queryPage(int pageNo, int pageSize);
}
