package com.alive_backend.entity.health_data;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "heartRate", schema = "health", catalog = "")
public class HeartRate {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "user_id")
    private int userId;
    @Basic
    @Column(name = "timeStamp")
    private Long timeStamp;
    @Basic
    @Column(name = "detail_value")
    private String detailValue;
}
