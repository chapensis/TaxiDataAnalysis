package com.yangchang.TaxiDataAnalysis.bean.po;

import lombok.Data;

@Data
public class RoadInfoPO {
    private int roadId;
    private String roadName;
    private double roadLon;
    private double roadLat;
    private int roadLength;
    private int roadTime;
}
