package com.example.microservice.impl;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class FileNIOImpl {

    public static void main(String[] args) {
        try {
            RandomAccessFile file = new RandomAccessFile("D:/test.txt", "rw");
            FileChannel channel = file.getChannel();
            ByteBuffer byteBuffer = ByteBuffer.allocate(4);
            try {
                int pos = channel.read(byteBuffer);
                while (pos != -1){
                    System.out.println("pos:"+pos);
                    // 首先读取数据到Buffer，然后反转Buffer，接着再从Buffer中读取数据。
                    byteBuffer.flip();
                    while (byteBuffer.hasRemaining()){
                        System.out.println((char)byteBuffer.get());
                    }

                    byteBuffer.clear();
                    pos = channel.read(byteBuffer);
                }
                file.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}