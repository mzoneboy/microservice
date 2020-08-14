package com.example.microservice.mapper;

import com.example.microservice.Entity.Student;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface StudentMapper extends Mapper<Student> {
}
