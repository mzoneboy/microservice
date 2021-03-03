package com.example.microservice.Entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Setter
@Getter
@Table(name = "student")
public class Student implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "JDBC")
    @NotNull
    private Long id;

    @Column(name="name")
    @Length(max=5, message = "姓名不能超过5个字符")
    private String name;

    @Column(name="age")
    private Integer age;

    public Student(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public Student(){};
}
