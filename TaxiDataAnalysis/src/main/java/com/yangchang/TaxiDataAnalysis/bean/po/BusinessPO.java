package com.yangchang.TaxiDataAnalysis.bean.po;

import com.yangchang.TaxiDataAnalysis.bean.Pagination;
import lombok.Data;

import java.util.Date;

@Data
public class BusinessPO {
    private Integer businessId;

    private Date stamp;

    private Integer unitId;

    private Date onTime;

    private Double onLon;

    private Double onLat;

    private Date offTime;

    private Double offLon;

    private Double offLat;

    private Integer runLength;

    private Integer runMoney;

    private Integer runTime;

    private Pagination pagination;
}
