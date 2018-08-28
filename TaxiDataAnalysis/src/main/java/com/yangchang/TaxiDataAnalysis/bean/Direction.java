package com.yangchang.TaxiDataAnalysis.bean;

import lombok.Data;

@Data
public class Direction {
    private int a;

    private float reward;

    public Direction(int a, float reward) {
        this.a = a;
        this.reward = reward;
    }
}
