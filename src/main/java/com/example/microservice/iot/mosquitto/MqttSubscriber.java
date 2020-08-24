package com.example.microservice.iot.mosquitto;

import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MqttSubscriber {
    private static final Logger logger = LoggerFactory.getLogger(MqttSubscriber.class);
    //tcp://MQTT安装的服务器地址:MQTT定义的端口号
    public static final String HOST = "tcp://localhost:1883";

    //定义MQTT的ID，可以在MQTT服务配置中指定
    private static final String clientid = "server33";

    private MqttClient client;
    private String userName = "mosquitto";
    private String passWord = "";

    /**
     * 构造函数
     * @throws MqttException
     */
    public MqttSubscriber() throws MqttException {
        // MemoryPersistence设置clientid的保存形式，默认为以内存保存
        client = new MqttClient(HOST, clientid, new MemoryPersistence());
        connect();
    }

    /**
     *  用来连接服务器
     */
    private void connect() {
        MqttConnectOptions options = new MqttConnectOptions();
        options.setCleanSession(false);
        options.setUserName(userName);
        options.setPassword(passWord.toCharArray());
        // 设置超时时间
        options.setConnectionTimeout(10);
        // 设置会话心跳时间
        options.setKeepAliveInterval(20);
        options.setAutomaticReconnect(true);
        try {
            System.out.println("客户端开始建立连接");
            client.setCallback(new PushCallback());
            client.connect(options);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param topic
     * @throws MqttPersistenceException
     * @throws MqttException
     */
    public void subscribe(String topic) throws MqttPersistenceException,
            MqttException {
        if(null == client.getTopic(topic)){
            client.subscribe(topic, 1);
            logger.info("subscribe {} completely! "
                    , topic);
        }

    }


    public static void main(String[] args) {
        MqttSubscriber mqttSubscriber;
        try {
            mqttSubscriber = new MqttSubscriber();
            mqttSubscriber.subscribe("newone");
           /* mqttSubscriber.client.publish("newone", "are u ok".getBytes(), 0, false);
            System.out.println("发送成功:"+"are u ok");
            while (true){
                Thread.sleep(10L);
            }*/

        } catch (MqttException  e) {
            e.printStackTrace();
        }



    }
}
