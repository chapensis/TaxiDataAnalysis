package com.yangchang.TaxiDataAnalysis.bean;

import lombok.Data;

@Data
public class CoordinateBean {
    private int x;
    private int y;
    private String lon;
    private String lat;
    private int roadpointbelong;

    public CoordinateBean() {
    }

    public CoordinateBean(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public CoordinateBean(String lon, String lat) {
        this.lon = lon;
        this.lat = lat;
    }

    public void setXY(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
