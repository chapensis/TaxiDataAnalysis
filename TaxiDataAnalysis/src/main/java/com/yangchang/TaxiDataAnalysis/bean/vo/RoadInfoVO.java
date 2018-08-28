package com.yangchang.TaxiDataAnalysis.bean.vo;

import com.yangchang.TaxiDataAnalysis.bean.po.RoadInfoPO;
import lombok.Data;
import org.springframework.beans.BeanUtils;

@Data
public class RoadInfoVO {
    private int roadId;
    private String roadName;
    private double roadLon;
    private double roadLat;
    private int roadLength;
    private int roadTime;

    private Pagination pagination;

    public static RoadInfoVO parseVO(RoadInfoPO roadInfoPO) {
        RoadInfoVO roadInfoVO = new RoadInfoVO();
        BeanUtils.copyProperties(roadInfoPO, roadInfoVO);

        return roadInfoVO;
    }
}
