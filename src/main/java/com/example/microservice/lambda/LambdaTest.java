package com.example.microservice.lambda;

import com.example.microservice.Entity.Student;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LambdaTest {
    public static void main(String[] args) {
        AddService addService = (a, b) -> a + b;
        System.out.println("1+2="+addService.add(1,2));
        PrintService printService = abc -> System.out.println(abc);
        printService.say("hello,world");
    }

    interface AddService{
        int add(int a, int b);
    }

    interface PrintService{
        void say(String msg);
    }
}
