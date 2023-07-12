package com.alive_backend.entity.health_data;

import com.alive_backend.utils.enumerate.FoodTypeEnum;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;
import java.util.UUID;

@Entity
@Setter
@Getter
@Table(name = "diet",schema = "health")
public class Diet {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", columnDefinition = "BINARY(16)")
    private UUID id;

    @Column(name = "user_id")
    private UUID userId;

    @Column(name = "food_id")
    private UUID foodId;

    @Column(name = "amount")
    private double amount;

    @Column(name = "date")
    private Date date;

    //定义一个枚举类型，表示饮食类型，暂定为早餐，午餐，晚餐，加餐
    @Column(name = "type")
    private FoodTypeEnum type;

}
