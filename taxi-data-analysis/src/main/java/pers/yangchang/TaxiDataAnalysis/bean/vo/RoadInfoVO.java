package pers.yangchang.TaxiDataAnalysis.bean.vo;

import pers.yangchang.TaxiDataAnalysis.bean.Pagination;
import pers.yangchang.TaxiDataAnalysis.bean.po.RoadInfoPO;
import lombok.Data;
import org.springframework.beans.BeanUtils;

/**
 * @author yangchang
 * 路段信息
 */
@Data
public class RoadInfoVO {
    private Integer roadId;
    private String roadName;
    private Double roadLon;
    private Double roadLat;
    private Integer roadLength;
    private Integer roadTime;

    private Pagination pagination;

    /**
     * po转换成vo
     *
     * @param po po
     * @return vo
     */
    public static RoadInfoVO parseVO(RoadInfoPO po) {
        if (po == null) {
            return null;
        }
        RoadInfoVO vo = new RoadInfoVO();
        BeanUtils.copyProperties(po, vo);

        return vo;
    }

    /**
     * vo转换成po
     *
     * @return po
     */
    public RoadInfoPO toPO() {
        RoadInfoPO roadInfoPO = new RoadInfoPO();
        BeanUtils.copyProperties(this, roadInfoPO);

        return roadInfoPO;
    }
}
