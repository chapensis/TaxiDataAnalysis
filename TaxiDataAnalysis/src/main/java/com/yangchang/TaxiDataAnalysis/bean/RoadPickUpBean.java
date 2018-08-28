package com.yangchang.TaxiDataAnalysis.bean;

import lombok.Data;

@Data
public class RoadPickUpBean {
    private int x;
    private int y;
    private int roadid;
    private int day;
    private int minu;
    private int count;
    private String pickUpStrings;

}
