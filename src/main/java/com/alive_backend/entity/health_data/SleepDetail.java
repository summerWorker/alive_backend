package com.alive_backend.entity.health_data;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
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
    @Column(name = "date")
    private Date date;
    @Basic
    @Column(name = "detail_value")
    private String detailValue;
    @Basic
    @Column(name = "length")
    private int length;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDetailValue() {
        return detailValue;
    }

    public void setDetailValue(String detailValue) {
        this.detailValue = detailValue;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SleepDetail that = (SleepDetail) o;
        return id == that.id && userId == that.userId && length == that.length && Objects.equals(date, that.date) && Objects.equals(detailValue, that.detailValue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId,  date, detailValue, length);
    }
}
