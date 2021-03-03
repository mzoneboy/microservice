package com.example.microservice.mybatis;


import com.example.microservice.Entity.Student;
import com.example.microservice.dao.IStudentDao;
import com.example.microservice.dao.StudentDao;
import com.example.microservice.mapper.StudentMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class SqlSessionTest {
    public static void main(String[] args) throws IOException {
        String resource = "mysql-config.xml";
        //读取mybatis-config配置文件
        InputStream inputStream = Resources.getResourceAsStream(resource);
        //创建SqlSessionFactory对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        //创建SqlSession对象
        SqlSession session = sqlSessionFactory.openSession();

        IStudentDao studentDao = session.getMapper(IStudentDao.class);
        List<Student> studentList = studentDao.queryList();
        session.close();
        inputStream.close();
    }
}