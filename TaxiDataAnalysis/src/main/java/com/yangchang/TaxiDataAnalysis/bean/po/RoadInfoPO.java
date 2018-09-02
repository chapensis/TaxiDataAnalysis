package com.yangchang.TaxiDataAnalysis.bean.po;

import com.yangchang.TaxiDataAnalysis.bean.Pagination;
import lombok.Data;

@Data
public class RoadInfoPO {
    private Integer roadId;
    private String roadName;
    private Double roadLon;
    private Double roadLat;
    private Integer roadLength;
    private Integer roadTime;

    private Pagination pagination;
}
