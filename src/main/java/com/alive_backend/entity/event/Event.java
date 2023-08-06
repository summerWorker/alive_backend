package com.alive_backend.entity.event;

import java.util.Date;
import java.util.Map;
import java.util.UUID;

public class Event {
    String topic; // 事件类型
    int user_id; // 用户id
    UUID uuid; // 事件id

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    Date date; // 事件发生时间
    Map<String, Object> data; // 事件数据

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Event{" +
                "topic='" + topic + '\'' +
                ", user_id=" + user_id +
                ", uuid=" + uuid +
                ", date=" + date +
                ", data=" + data +
                '}';
    }
}
