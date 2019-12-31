package pers.yangchang.TaxiDataAnalysis.bean.po;

import pers.yangchang.TaxiDataAnalysis.bean.Pagination;
import lombok.Data;

@Data
public class RoadTripInfoPO {
    
    private Integer tripId;

    private RoadInfoPO startRoadInfo;

    private RoadInfoPO endRoadInfo;

    private Integer timeslot;

    private Integer totalTime;

    private Integer totalMoney;

    private Integer totalNum;

    private Pagination pagination;

}
