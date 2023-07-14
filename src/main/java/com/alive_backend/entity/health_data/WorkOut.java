package com.alive_backend.entity.health_data;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;
import java.util.UUID;

@Entity
@Setter
@Getter
@Table(name = "workout", schema = "health")
public class WorkOut {
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
    @Column(name = "exercise_id", columnDefinition = "BINARY(16)")
    private UUID exerciseId;

    @Basic
    @Column(name = "user_id")
    private int userId;
}