package com.alive_backend.entity.health_data;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "steps", schema = "health")
public class Steps {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "steps_id", columnDefinition = "BINARY(16)")
    private UUID id;

    @Column(name = "user_id")
    private int userId;

    @Column(name = "date")
    private Date date;

    @Column(name = "step")
    private int step;

    @Column(name = "goal")
    private int goal;

    @Column(name = "distance")
    private int distance;

    @Column(name = "calories")
    private int calories;

    public Steps() {
    }

    public Steps(int userId, Date date, int step, int goal, int distance, int calories) {
        this.userId = userId;
        this.date = date;
        this.step = step;
        this.goal = goal;
        this.distance = distance;
        this.calories = calories;
    }
}
