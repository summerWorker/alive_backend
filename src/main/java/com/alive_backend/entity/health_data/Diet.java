package com.alive_backend.entity.health_data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
import java.util.Arrays;
import java.util.Objects;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Diet {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", columnDefinition = "BINARY(16)")
    private UUID id;

    @Basic
    @Column(name = "amount")
    private double amount;
    @Basic
    @Column(name = "date")
    private Date date;
    @Basic
    @Column(name = "food_id", columnDefinition = "BINARY(16)")
    private UUID foodId;
    @Basic
    @Column(name = "type")
    private int type;
    @Basic
    @Column(name = "user_id")
    private int userId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Diet diet = (Diet) o;

        if (Double.compare(diet.amount, amount) != 0) return false;
        if (type != diet.type) return false;
        if (userId != diet.userId) return false;
        if (!Objects.equals(id, diet.id)) return false;
        if (date != null ? !date.equals(diet.date) : diet.date != null) return false;
        if (!Objects.equals(foodId, diet.foodId)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, amount, date, foodId, type, userId);
    }
}
