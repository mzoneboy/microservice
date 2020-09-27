package com.example.microservice.concurrent;

public class WaitThread extends Thread {
    private int time;
    public WaitThread(String name, int time) {
        super(name);
        this.time = time;
    }

    public synchronized void run() {

        try {

            if("thread3".equals(this.getName())||"thread4".equals(this.getName())){
                this.sleep(time);
            } else {
                this.wait(time);
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(this.getName() + "开始执行");
    }
}
