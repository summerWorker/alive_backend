package com.alive_backend.entity.health_data;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

@Entity
public class Height {
    @Basic
    @Column(name = "user_id")
    private int userId;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", columnDefinition = "BINARY(16)")
    private UUID id;
    @Basic
    @Column(name = "height")
    private double height;
    @Basic
    @Column(name = "date")
    private Date date;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Height height1 = (Height) o;

        if (userId != height1.userId) return false;
        if (Double.compare(height1.height, height) != 0) return false;
        if (!Objects.equals(id, height1.id)) return false;
        if (date != null ? !date.equals(height1.date) : height1.date != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, height, date);
    }
}
