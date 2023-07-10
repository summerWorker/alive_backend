package com.alive_backend.entity.health_data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Weight {
    @Basic
    @Column(name = "user_id")
    private int userId;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", columnDefinition = "BINARY(16)")
    private UUID id;
    @Basic
    @Column(name = "weight")
    private double weight;
    @Basic
    @Column(name = "date")
    private Date date;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
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

        Weight weight1 = (Weight) o;

        if (userId != weight1.userId) return false;
        if (Double.compare(weight1.weight, weight) != 0) return false;
        if (!Objects.equals(id, weight1.id)) return false;
        if (date != null ? !date.equals(weight1.date) : weight1.date != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, weight, date);
    }
}
