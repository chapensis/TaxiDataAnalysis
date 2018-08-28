package com.yangchang.TaxiDataAnalysis.bean;

import lombok.Data;

@Data
public class MapMatchingBean {

    private int x;

    private int y;

    private int roadbelong;

    public MapMatchingBean() {
    }

    public MapMatchingBean(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
