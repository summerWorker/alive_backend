package com.alive_backend.entity.health_data;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "main_record", schema = "health", catalog = "")
public class MainRecord {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "user_id")
    private int userId;
    @Basic
    @Column(name = "height")
    private Double height;
    @Basic
    @Column(name = "weight")
    private Integer weight;
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
    @Column(name = "pressure")
    private Double pressure;
    @Basic
    @Column(name = "heart_rate")
    private Double heartRate;
    @Basic
    @Column(name = "health_score")
    private Integer healthScore;
    @Basic
    @Column(name = "health_advice")
    private String healthAdvice;
    @Basic
    @Column(name = "update_time")
    private Timestamp updateTime;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Double getExerciseTime() {
        return exerciseTime;
    }

    public void setExerciseTime(Double exerciseTime) {
        this.exerciseTime = exerciseTime;
    }

    public Double getCalorieIn() {
        return calorieIn;
    }

    public void setCalorieIn(Double calorieIn) {
        this.calorieIn = calorieIn;
    }

    public Double getCalorieConsume() {
        return calorieConsume;
    }

    public void setCalorieConsume(Double calorieConsume) {
        this.calorieConsume = calorieConsume;
    }

    public Double getSleepTime() {
        return sleepTime;
    }

    public void setSleepTime(Double sleepTime) {
        this.sleepTime = sleepTime;
    }

    public Double getPressure() {
        return pressure;
    }

    public void setPressure(Double pressure) {
        this.pressure = pressure;
    }

    public Double getHeartRate() {
        return heartRate;
    }

    public void setHeartRate(Double heartRate) {
        this.heartRate = heartRate;
    }

    public Integer getHealthScore() {
        return healthScore;
    }

    public void setHealthScore(Integer healthScore) {
        this.healthScore = healthScore;
    }

    public String getHealthAdvice() {
        return healthAdvice;
    }

    public void setHealthAdvice(String healthAdvice) {
        this.healthAdvice = healthAdvice;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MainRecord that = (MainRecord) o;

        if (userId != that.userId) return false;
        if (height != null ? !height.equals(that.height) : that.height != null) return false;
        if (weight != null ? !weight.equals(that.weight) : that.weight != null) return false;
        if (exerciseTime != null ? !exerciseTime.equals(that.exerciseTime) : that.exerciseTime != null) return false;
        if (calorieIn != null ? !calorieIn.equals(that.calorieIn) : that.calorieIn != null) return false;
        if (calorieConsume != null ? !calorieConsume.equals(that.calorieConsume) : that.calorieConsume != null)
            return false;
        if (sleepTime != null ? !sleepTime.equals(that.sleepTime) : that.sleepTime != null) return false;
        if (pressure != null ? !pressure.equals(that.pressure) : that.pressure != null) return false;
        if (heartRate != null ? !heartRate.equals(that.heartRate) : that.heartRate != null) return false;
        if (healthScore != null ? !healthScore.equals(that.healthScore) : that.healthScore != null) return false;
        if (healthAdvice != null ? !healthAdvice.equals(that.healthAdvice) : that.healthAdvice != null) return false;
        if (updateTime != null ? !updateTime.equals(that.updateTime) : that.updateTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userId;
        result = 31 * result + (height != null ? height.hashCode() : 0);
        result = 31 * result + (weight != null ? weight.hashCode() : 0);
        result = 31 * result + (exerciseTime != null ? exerciseTime.hashCode() : 0);
        result = 31 * result + (calorieIn != null ? calorieIn.hashCode() : 0);
        result = 31 * result + (calorieConsume != null ? calorieConsume.hashCode() : 0);
        result = 31 * result + (sleepTime != null ? sleepTime.hashCode() : 0);
        result = 31 * result + (pressure != null ? pressure.hashCode() : 0);
        result = 31 * result + (heartRate != null ? heartRate.hashCode() : 0);
        result = 31 * result + (healthScore != null ? healthScore.hashCode() : 0);
        result = 31 * result + (healthAdvice != null ? healthAdvice.hashCode() : 0);
        result = 31 * result + (updateTime != null ? updateTime.hashCode() : 0);
        return result;
    }
}
