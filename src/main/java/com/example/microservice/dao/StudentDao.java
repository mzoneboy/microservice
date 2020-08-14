package com.example.microservice.dao;

import com.example.microservice.Entity.Student;
import com.example.microservice.mapper.StudentMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Repository
@Transactional
public class StudentDao implements IStudentDao {
    @Autowired
    private StudentMapper studentMapper;
    @Override
    public int insert(Student student) {
        return studentMapper.insert(student);
    }

    public List<Student> queryList() {
        List<Student> list = studentMapper.selectAll();
        return list;
    }

    @Override
    public PageInfo<Student> queryPage(int pageNo, int pageSize) {
        PageHelper.startPage(pageNo, pageSize);
        List<Student> list = studentMapper.selectAll();
        PageInfo pageInfo = new PageInfo<>(list);
        return pageInfo;
    }
}
