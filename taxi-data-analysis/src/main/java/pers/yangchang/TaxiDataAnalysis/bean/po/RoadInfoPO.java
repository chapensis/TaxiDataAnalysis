package pers.yangchang.TaxiDataAnalysis.bean.po;

import pers.yangchang.TaxiDataAnalysis.bean.Pagination;
import lombok.Data;

import java.io.Serializable;

@Data
public class RoadInfoPO implements Serializable {
    private Integer roadId;
    private String roadName;
    private Double roadLon;
    private Double roadLat;
    private Integer roadLength;
    private Integer roadTime;

    private Pagination pagination;
}
