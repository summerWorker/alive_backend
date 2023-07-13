package com.alive_backend.entity.health_data;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;
import java.util.UUID;

@Entity
@Getter
@Setter
public class BloodPressure {
    @Basic
    @Column(name = "user_id")
    private int userId;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", columnDefinition = "BINARY(16)")
    private UUID id;
    @Basic
    @Column(name = "systolic")
    private int systolic;
    @Basic
    @Column(name = "diastolic")
    private int diastolic;
    @Basic
    @Column(name = "date")
    private Date date;
}
