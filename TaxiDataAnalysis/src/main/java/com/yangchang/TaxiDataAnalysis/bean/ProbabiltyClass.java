package com.yangchang.TaxiDataAnalysis.bean;

import lombok.Data;

@Data
public class ProbabiltyClass {
    private int point;

    private float probability;

    public ProbabiltyClass(int point, float probability) {
        this.point = point;
        this.probability = probability;
    }
}
