package com.alive_backend.entity.health_data;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Data
@Table(name = "main_record", schema = "health", catalog = "")
public class MainRecord {
    @Basic
    @Column(name = "user_id")
    private int userId;
    @Basic
    @Column(name = "height")
    private Double height;
    @Basic
    @Column(name = "weight")
    private Double weight;
    @Basic
    @Column(name = "exercise_time")
    private Double exerciseTime;
    @Basic
    @Column(name = "calorie_in")
    private Double calorieIn;
    @Basic
    @Column(name = "calorie_consume")
    private Double calorieConsume;
    @Basic
    @Column(name = "sleep_time")
    private Double sleepTime;
    @Basic
    @Column(name = "systolic_pressure")
    private Double systolicPressure;
    @Basic
    @Column(name = "diastolic_pressure")
    private Double diastolicPressure;
    @Basic
    @Column(name = "blood_sugar")
    private Double bloodSugar;
    @Basic
    @Column(name = "heart_rate")
    private Integer heartRate;
    @Basic
    @Column(name = "health_score")
    private Integer healthScore;
    @Basic
    @Column(name = "health_advice")
    private String healthAdvice;
    @Basic
    @Column(name = "steps")
    private Integer steps;
    @Basic
    @Column(name = "update_time")
    private Timestamp updateTime;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "record_id")
    private int recordId;

//    public int getUserId() {
//        return userId;
//    }
//
//    public void setUserId(int userId) {
//        this.userId = userId;
//    }
//
//    public Double getHeight() {
//        return height;
//    }
//
//    public void setHeight(Double height) {
//        this.height = height;
//    }
//
//    public Integer getWeight() {
//        return weight;
//    }
//
//    public void setWeight(Integer weight) {
//        this.weight = weight;
//    }
//
//    public Double getExerciseTime() {
//        return exerciseTime;
//    }
//
//    public void setExerciseTime(Double exerciseTime) {
//        this.exerciseTime = exerciseTime;
//    }
//
//    public Double getCalorieIn() {
//        return calorieIn;
//    }
//
//    public void setCalorieIn(Double calorieIn) {
//        this.calorieIn = calorieIn;
//    }
//
//    public Double getCalorieConsume() {
//        return calorieConsume;
//    }
//
//    public void setCalorieConsume(Double calorieConsume) {
//        this.calorieConsume = calorieConsume;
//    }
//
//    public Double getSleepTime() {
//        return sleepTime;
//    }
//
//    public void setSleepTime(Double sleepTime) {
//        this.sleepTime = sleepTime;
//    }
//
//    public Double getPressure() {
//        return pressure;
//    }
//
//    public void setPressure(Double pressure) {
//        this.pressure = pressure;
//    }
//
//    public Double getHeartRate() {
//        return heartRate;
//    }
//
//    public void setHeartRate(Double heartRate) {
//        this.heartRate = heartRate;
//    }
//
//    public Integer getHealthScore() {
//        return healthScore;
//    }
//
//    public void setHealthScore(Integer healthScore) {
//        this.healthScore = healthScore;
//    }
//
//    public String getHealthAdvice() {
//        return healthAdvice;
//    }
//
//    public void setHealthAdvice(String healthAdvice) {
//        this.healthAdvice = healthAdvice;
//    }
//
//    public Timestamp getUpdateTime() {
//        return updateTime;
//    }
//
//    public void setUpdateTime(Timestamp updateTime) {
//        this.updateTime = updateTime;
//    }
//
//    public int getRecordId() {
//        return recordId;
//    }
//
//    public void setRecordId(int recordId) {
//        this.recordId = recordId;
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        MainRecord that = (MainRecord) o;
//        return userId == that.userId && recordId == that.recordId && Objects.equals(height, that.height) && Objects.equals(weight, that.weight) && Objects.equals(exerciseTime, that.exerciseTime) && Objects.equals(calorieIn, that.calorieIn) && Objects.equals(calorieConsume, that.calorieConsume) && Objects.equals(sleepTime, that.sleepTime) && Objects.equals(pressure, that.pressure) && Objects.equals(heartRate, that.heartRate) && Objects.equals(healthScore, that.healthScore) && Objects.equals(healthAdvice, that.healthAdvice) && Objects.equals(updateTime, that.updateTime);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(userId, height, weight, exerciseTime, calorieIn, calorieConsume, sleepTime, pressure, heartRate, healthScore, healthAdvice, updateTime, recordId);
//    }
}
