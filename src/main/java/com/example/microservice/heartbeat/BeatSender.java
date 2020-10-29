package com.example.microservice.heartbeat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

public class BeatSender {
    private Logger logger = LoggerFactory.getLogger(BeatSender.class);
    private ScheduledExecutorService executorService;

    public BeatSender() {
        this.executorService = new ScheduledThreadPoolExecutor(3, new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                Thread thread = new Thread(r);
                thread.setDaemon(true);
                thread.setName("sendBeatThread");
                return thread;
            }
        });
    }

    public void addBeatInfo(BeatInfo beatInfo){
        logger.info("add and send heart beat to server");
        executorService.schedule(new BeatTask(beatInfo), 3, TimeUnit.SECONDS);
    }

    class BeatTask implements Runnable{
        BeatInfo beatInfo;

        public BeatTask(BeatInfo beatInfo) {
            this.beatInfo = beatInfo;
        }


        @Override
        public void run() {
            String serverAddr = beatInfo.getIp() + ":" + beatInfo.getPort();
            logger.info("{} send heart beat to server, id={}, name={}", serverAddr, Thread.currentThread().getId(), Thread.currentThread().getName());
            executorService.schedule(new BeatTask(beatInfo), 3, TimeUnit.SECONDS);
        }
    }
}
