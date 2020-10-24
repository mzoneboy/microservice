package com.example.microservice.lambda;


import com.example.microservice.Entity.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.naming.Name;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamTest {
    private static final Logger logger = LoggerFactory.getLogger(StreamTest.class);
    public static void main(String[] args) {
        List<Student> list = new ArrayList<>();
        Student student = new Student("lily", 23);
        list.add(student);
        student = new Student("jim", 20);
        list.add(student);
        student=new Student("bob", 19);
        list.add(student);
        list.stream().forEach((s) -> {
            System.out.println(String.format("%s,%s", s.getName(),  String.valueOf(s.getAge())));
        });

        List<Student> sortedList = list.stream().sorted(Comparator.comparing(Student::getAge)).collect(Collectors.toList());
        sortedList.stream().forEach((s) -> {
            System.out.println(String.format("%s,%s", s.getName(),  String.valueOf(s.getAge())));
        });

        List<Student> filterList = list.stream().filter(s -> s.getAge()>=20).collect(Collectors.toList());
        filterList.stream().forEach((s) -> {
            System.out.println(String.format("%s,%s", s.getName(),  String.valueOf(s.getAge())));
        });

        Stream.of("mick,25","wood,30").map(new Function<String, Student>() {
            public Student apply(String s){
                String[] array = s.split(",");
                Student student1 = new Student(array[0], Integer.parseInt(array[1]));
                return student1;
            }
        }).forEach(student1 -> System.out.println(student1.getName()+":"+student1.getAge()));

        System.out.println("-------------------------------------");
        list.parallelStream().forEach(s->{s.setAge(s.getAge()+1);logger.info("{},{}", s.getName(), s.getAge());});

    }
}
