package com.yangchang.TaxiDataAnalysis.bean;

import lombok.Data;

/**
 * 最原始的数据表
 */
@Data
public class T_Businesshistory_Bean {
    // 车牌号
    private String unit_id;
    // 乘客上车位置经度
    private double ONLON;
    // 乘客上车位置纬度
    private double ONLAT;
    // 乘客下车位置经度
    private double OFFLON;
    // 乘客下车位置纬度
    private double OFFLAT;
    // 运行里程
    private double RUNLEN;
    // 运行时间
    private int time;
    // 运行花费
    private int money;

    public T_Businesshistory_Bean() {

    }

    public T_Businesshistory_Bean(double oNLON, double oNLAT) {
        super();
        ONLON = oNLON;
        ONLAT = oNLAT;
    }

    public T_Businesshistory_Bean(double oNLON, double oNLAT, double offlon, double offlat) {
        super();
        OFFLON = offlon;
        OFFLAT = offlat;
    }
}
