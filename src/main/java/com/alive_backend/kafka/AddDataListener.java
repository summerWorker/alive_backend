package com.alive_backend.kafka;

import com.alibaba.fastjson2.JSONObject;
import com.alive_backend.entity.event.Event;
import com.alive_backend.service.health_data.BloodPressureService;
import com.alive_backend.service.health_data.BloodSugarService;
import com.alive_backend.service.health_data.HeightService;
import com.alive_backend.service.health_data.WeightService;
import com.alive_backend.utils.constant.TopicConstant;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;


@Component
public class AddDataListener {
    @Autowired
    private BloodSugarService bloodSugarService;
    @Autowired
    private BloodPressureService bloodPressureService;
    @Autowired
    private HeightService heightService;
    @Autowired
    private WeightService weightService;
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @KafkaListener(topics = {TopicConstant.BLOOD_SUGAR_TOPIC, TopicConstant.BLOOD_PRESSURE_TOPIC, TopicConstant.HEIGHT_TOPIC, TopicConstant.WEIGHT_TOPIC})
    public void addDataListener(ConsumerRecord record) throws Exception{
        if(record == null || record.value() == null){
            return;
        }
        //turn String to Event
        Event event = JSONObject.parseObject(record.value().toString(), Event.class);
        if(event == null){
            return;
        }
        //调用相关Service方法将数据存入数据库
        if(event.getTopic().equals(TopicConstant.BLOOD_SUGAR_TOPIC)){
            bloodSugarService.addBloodSugar(event.getUser_id(), event.getDate(), event.getData());
        }else if(event.getTopic().equals(TopicConstant.BLOOD_PRESSURE_TOPIC)) {
            bloodPressureService.addBloodPressure(event.getUser_id(), event.getDate(), event.getData());
        }else if(event.getTopic().equals(TopicConstant.HEIGHT_TOPIC)){
            heightService.addHeight(event.getUser_id(), event.getDate(), event.getData());
        }else if(event.getTopic().equals(TopicConstant.WEIGHT_TOPIC)){
            weightService.addWeight(event.getUser_id(), event.getDate(), event.getData());
        }

        kafkaTemplate.send(TopicConstant.ADD_DATA_DONE, event.getUuid().toString());
    }

    @KafkaListener(topics = TopicConstant.ADD_DATA_DONE)
    public void addDataDoneListener(ConsumerRecord record) throws Exception{
        if(record == null || record.value() == null){
            return;
        }
        //获取uuid
        UUID uuid = UUID.fromString(record.value().toString());
        //websocket 向前端发送已完成的消息（前后端通过uuid确定会话）
        //TODO
    }

}
