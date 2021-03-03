package com.example.microservice.concurrent;


public class IncThread implements Runnable {
    int x=1;
    int y=1;
    @Override
    public void run() {
        if (x==2) x=0;
        else incX();
        if (y==2) y=0;
        else y++;
    }

    public synchronized void incX(){
        x=x+1;
    }

    public static void main(String[] args) throws InterruptedException{
        IncThread thread1 = new IncThread();
        IncThread thread2 = new IncThread();
        while (true){
            thread1.run();
            thread2.run();

            Thread.sleep(10L);
            System.out.println("thread1, x="+thread1.x+",y="+thread1.y);
            System.out.println("thread2, x="+thread2.x+",y="+thread2.y);
        }
    }
}