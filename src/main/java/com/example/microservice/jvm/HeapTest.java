package com.example.microservice.jvm;

import java.util.ArrayList;

public class HeapTest {
    byte[] ONE_MB_SIZE = new byte[1024*1024];

    public static void main(String[] args) throws InterruptedException {
        ArrayList<HeapTest> list = new ArrayList<>();
        while (true){
            list.add(new HeapTest());
            Thread.sleep(20L);
        }
    }
}