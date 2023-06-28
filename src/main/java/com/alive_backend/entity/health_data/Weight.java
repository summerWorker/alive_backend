package com.alive_backend.entity.health_data;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Weight {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "user_id")
    private int userId;
    @Basic
    @Column(name = "year_id")
    private int yearId;
    @Basic
    @Column(name = "detail_value")
    private String detailValue;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getYearId() {
        return yearId;
    }

    public void setYearId(int yearId) {
        this.yearId = yearId;
    }

    public String getDetailValue() {
        return detailValue;
    }

    public void setDetailValue(String detailValue) {
        this.detailValue = detailValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Weight weight = (Weight) o;
        return id == weight.id && userId == weight.userId && yearId == weight.yearId && Objects.equals(detailValue, weight.detailValue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, yearId, detailValue);
    }
}
