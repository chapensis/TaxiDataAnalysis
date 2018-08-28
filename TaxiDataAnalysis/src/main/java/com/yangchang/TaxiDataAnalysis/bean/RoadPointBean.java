package com.yangchang.TaxiDataAnalysis.bean;

import lombok.Data;

/**
 * 坐标位置
 */
@Data
public class RoadPointBean {
    private int id;
    // 经度
    private double lon;
    // 纬度
    private double lat;
    // 所属的路段id
    private int belong;
}
