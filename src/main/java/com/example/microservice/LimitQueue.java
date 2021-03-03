package com.example.microservice;

import java.util.Arrays;

public class LimitQueue {
    private int[] array;
    private int size;
    private int index;

    public LimitQueue(int size) {
        array = new int[size];
        this.size = size;
        this.index = -1;
    }

    public void push(int value) {
        if (index < size - 1) {
            index++;
            array[index] = value;
        } else {
            System.out.println("数组已满");
        }
    }

    public int pop() {
        if (index >= 0) {
            int value = array[index];
            index--;
            System.out.println("数组弹出元素：" + value);
            return value;
        } else {
            System.out.println("数组没有可弹出的元素");
            return 0;
        }
    }

}