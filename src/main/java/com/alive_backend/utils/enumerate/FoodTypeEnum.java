package com.alive_backend.utils.enumerate;

import lombok.Getter;
import lombok.Setter;

@Getter
public enum FoodTypeEnum {
    BREAKFAST("早餐"),
    LUNCH("午餐"),
    DINNER("晚餐"),
    SNACK("加餐");

    private String name;

    FoodTypeEnum(String name) {
        this.name = name;
    }
}
