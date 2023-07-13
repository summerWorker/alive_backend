package com.alive_backend.entity.basic_data;


import com.alive_backend.entity.health_data.Diet;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "food",schema = "health")
public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", columnDefinition = "BINARY(16)")
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "picture")
    private String picture;

    //user_id为-1表示为公共食物，否则为用户自定义食物
    @Column(name = "user_id")
    private int userId;

    //碳水化合物，蛋白质，脂肪,热量，膳食纤维，钠
    //单位为克
    @Column(name = "carbohydrate")
    private double carbohydrate;

    //单位为克
    @Column(name = "protein")
    private double protein;

    //单位为克
    @Column(name = "fat")
    private double fat;

    //单位为千卡
    @Column(name = "calorie")
    private double calorie;

    //单位为克
    @Column(name = "dietary_fiber")
    private double dietaryFiber;

    //单位为毫克
    @Column(name = "sodium")
    private double sodium;


}
