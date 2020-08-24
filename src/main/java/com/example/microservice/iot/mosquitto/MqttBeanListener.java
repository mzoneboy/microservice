package com.example.microservice.iot.mosquitto;

import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class MqttBeanListener  implements MqttCallback {
    private static final Logger logger = LoggerFactory.getLogger(MqttBeanListener.class);
    private static MqttClient sampleClient ;

    //tcp://MQTT安装的服务器地址:MQTT定义的端口号
    public static final String HOST = "tcp://localhost:1883";

    //定义MQTT的ID，可以在MQTT服务配置中指定
    private static final String clientid = "listener11";

    private String userName = "mosquitto";
    private String passWord = "";

    @Override
    public void connectionLost(Throwable throwable) {
        try {
            if (sampleClient==null) {
                sampleClient = new MqttClient(HOST, clientid, new MemoryPersistence());
                sampleClient.setCallback(this);
            }
            sampleClient.connect(getOptions());
            logger.info("Mqtt服务器连接成功");
        } catch (MqttException e) {
            logger.error(e.toString());
        }
    }

    @Override
    public void messageArrived(String topic, MqttMessage mqttMessage) throws Exception {
        logger.info("Bean接收消息主题 : " + topic);
        logger.info("Bean接收消息Qos : " + mqttMessage.getQos());
        logger.info("Bean接收消息内容 : " + new String(mqttMessage.getPayload()));
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
        try {
            logger.info("发送完成"+iMqttDeliveryToken.getMessage());
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    @PostConstruct
    public void start() {
        try {
            MqttConnectOptions options = getOptions();
            sampleClient = new MqttClient(HOST, clientid, new MemoryPersistence());
            sampleClient.setCallback(this);
            MqttTopic topic = sampleClient.getTopic("newone");
            //setWill方法，如果项目中需要知道客户端是否掉线可以调用该方法。设置最终端口的通知消息
            options.setWill(topic, "close".getBytes(), 0, true);
            sampleClient.connect(options);
            sampleClient.subscribe("newone", 1);
        } catch (MqttException me) {
            logger.error("Connection failed");
        }
    }


    /**
     * @TODO（生成配置对象，用户名，密码等）
     * @return MqttConnectOptions
     */
    public MqttConnectOptions getOptions() {
        MqttConnectOptions connOpts = new MqttConnectOptions();
        connOpts.setCleanSession(true);
        connOpts.setUserName(userName);
        connOpts.setPassword(passWord.toCharArray());
        //超时设置
        connOpts.setConnectionTimeout(10);
        connOpts.setKeepAliveInterval(120);
        return connOpts;
    }

}
