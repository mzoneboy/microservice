package com.example.microservice.impl;


import java.io.*;
import java.util.Scanner;

public class FileIOImpl {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入字符串");
        String input = scanner.next();
        FileInputStream fis = null;
        FileOutputStream fos = null;
        File file = new File("D:/test.txt");
        try {
            byte[] buf = new byte[16];
            int length = 0;
            fis = new FileInputStream(file);
            StringBuilder stringBuilder =new StringBuilder();
            while ((length=fis.read(buf))!=-1){
                stringBuilder.append(new String(buf, 0, length));
            }

            System.out.println(stringBuilder.toString());

            stringBuilder.append(input);

            fos = new FileOutputStream(file);
            fos.write(stringBuilder.toString().getBytes());
        } catch (FileNotFoundException fnfe){
            System.out.println(fnfe);
        } catch (IOException ioe) {
            System.out.println(ioe);
        } finally {
            if(null != fis){
                try {
                    fis.close();
                } catch (IOException ioe){
                    System.out.println(ioe);
                }
            }

            if(null != fos) {
                try {
                    fos.close();
                } catch (IOException ioe){
                    System.out.println(ioe);
                }
            }
        }
    }
}