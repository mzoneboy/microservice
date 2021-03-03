package com.example.microservice.jvm;


import java.util.ArrayList;
import java.util.List;

public class JvmTest {
    private static final int ONE_MB_SIZE = 1024 * 1024;
    public static void main(String[] args) {
            System.out.println("==========5 YGC+1 OGC");
        System.out.println("=========={}"+ Math.pow(2, 2));

            List caches = new ArrayList();

            // 大概3M就会触发一次YGC
            for (int i = 0; i < 11; i++){
                caches.add(new byte[3*ONE_MB_SIZE]);
                System.out.println("i.---" + i);
            }

            System.out.println("===========continue OGC");

           caches.add(new byte[3*ONE_MB_SIZE]);

            caches.remove(0);
            caches.add(new byte[3*ONE_MB_SIZE]);

            // 清空后继续存入对象产生YGC
            for (int i = 0; i < 12; i++) {
                caches.remove(0);
            }

            //System.gc();

            for (int i = 0; i < 11; i++) {
                caches.add(new byte[3 * ONE_MB_SIZE]);
            }
            //caches.add(new byte[3 * ONE_MB_SIZE]);
    }
}
