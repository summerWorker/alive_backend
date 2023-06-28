package com.alive_backend.entity.health_data;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Data
@Table(name = "sleep_detail", schema = "health", catalog = "")
public class SleepDetail {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private long id;
    @Basic
    @Column(name = "user_id")
    private int userId;
    @Basic
    @Column(name = "health_record_id")
    private int healthRecordId;
    @Basic
    @Column(name = "week")
    private int week;
    @Basic
    @Column(name = "date")
    private Date date;
    @Basic
    @Column(name = "deep_period")
    private String deepPeriod;
    @Basic
    @Column(name = "shallow_period")
    private String shallowPeriod;
    @Basic
    @Column(name = "awake_period")
    private String awakePeriod;
    @Basic
    @Column(name = "dream_period")
    private String dreamPeriod;

//    public long getId() {
//        return id;
//    }
//
//    public void setId(long id) {
//        this.id = id;
//    }
//
//    public int getUserId() {
//        return userId;
//    }
//
//    public void setUserId(int userId) {
//        this.userId = userId;
//    }
//
//    public int getHealthRecordId() {
//        return healthRecordId;
//    }
//
//    public void setHealthRecordId(int healthRecordId) {
//        this.healthRecordId = healthRecordId;
//    }
//
//    public int getWeek() {
//        return week;
//    }
//
//    public void setWeek(int week) {
//        this.week = week;
//    }
//
//    public Date getDate() {
//        return date;
//    }
//
//    public void setDate(Date date) {
//        this.date = date;
//    }
//
//    public String getDeepPeriod() {
//        return deepPeriod;
//    }
//
//    public void setDeepPeriod(String deepPeriod) {
//        this.deepPeriod = deepPeriod;
//    }
//
//    public String getShallowPeriod() {
//        return shallowPeriod;
//    }
//
//    public void setShallowPeriod(String shallowPeriod) {
//        this.shallowPeriod = shallowPeriod;
//    }
//
//    public String getAwakePeriod() {
//        return awakePeriod;
//    }
//
//    public void setAwakePeriod(String awakePeriod) {
//        this.awakePeriod = awakePeriod;
//    }
//
//    public String getDreamPeriod() {
//        return dreamPeriod;
//    }
//
//    public void setDreamPeriod(String dreamPeriod) {
//        this.dreamPeriod = dreamPeriod;
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        SleepDetail that = (SleepDetail) o;
//        return id == that.id && userId == that.userId && healthRecordId == that.healthRecordId && week == that.week && Objects.equals(date, that.date) && Objects.equals(deepPeriod, that.deepPeriod) && Objects.equals(shallowPeriod, that.shallowPeriod) && Objects.equals(awakePeriod, that.awakePeriod) && Objects.equals(dreamPeriod, that.dreamPeriod);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(id, userId, healthRecordId, week, date, deepPeriod, shallowPeriod, awakePeriod, dreamPeriod);
//    }
}
