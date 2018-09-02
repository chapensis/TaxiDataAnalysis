package com.yangchang.TaxiDataAnalysis.bean.vo;

import com.yangchang.TaxiDataAnalysis.bean.Pagination;
import com.yangchang.TaxiDataAnalysis.bean.po.RoadTripInfoPO;
import lombok.Data;
import org.springframework.beans.BeanUtils;

@Data
public class RoadTripInfoVO {
    private Integer tripId;

    private RoadInfoVO startRoadInfo;

    private RoadInfoVO endRoadInfo;

    private Integer timeslot;

    private Integer totalTime;

    private Integer totalMoney;

    private Integer totalNum;

    private Pagination pagination;

    /**
     * po转换成vo
     *
     * @param po po
     * @return vo
     */
    public static RoadTripInfoVO parseVO(RoadTripInfoPO po) {
        if (po == null) {
            return null;
        }
        RoadTripInfoVO vo = new RoadTripInfoVO();
        BeanUtils.copyProperties(po, vo, "startRoadInfo", "endRoadInfo");
        vo.setStartRoadInfo(RoadInfoVO.parseVO(po.getStartRoadInfo()));
        vo.setEndRoadInfo(RoadInfoVO.parseVO(po.getEndRoadInfo()));
        return vo;
    }

    /**
     * vo转换成po
     *
     * @return po
     */
    public RoadTripInfoPO toPO() {
        RoadTripInfoPO po = new RoadTripInfoPO();
        BeanUtils.copyProperties(this, po, "startRoadInfo", "endRoadInfo");
        if (this.getStartRoadInfo() != null) {
            po.setStartRoadInfo(this.getStartRoadInfo().toPO());
        }
        if (this.getEndRoadInfo() != null) {
            po.setEndRoadInfo(this.getEndRoadInfo().toPO());
        }

        return po;
    }
}
