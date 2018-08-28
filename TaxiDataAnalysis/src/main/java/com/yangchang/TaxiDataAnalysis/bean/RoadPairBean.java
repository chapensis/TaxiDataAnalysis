package com.yangchang.TaxiDataAnalysis.bean;

import lombok.Data;

@Data
public class RoadPairBean {
    private int startRoadid;
    private int endRoadid;
    private double startRoadLon;
    private double startRoadLat;
    private double endRoadLon;
    private double endRoadLat;
    private int number;
    private int drivingtime;
    private float money;
    private int day;
}
