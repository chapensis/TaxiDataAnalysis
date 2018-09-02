package com.yangchang.TaxiDataAnalysis.bean.vo;

import com.yangchang.TaxiDataAnalysis.bean.po.RoadListInfoPO;
import lombok.Data;
import org.springframework.beans.BeanUtils;

@Data
public class RoadListInfoVO {
    private Integer roadListNum;

    /**
     * po转换成vo
     * @param po po
     * @return vo
     */
    public static RoadListInfoVO parseVO(RoadListInfoPO po) {
        if (po == null) {
            return null;
        }
        RoadListInfoVO vo = new RoadListInfoVO();
        BeanUtils.copyProperties(po, vo);

        return vo;
    }
}
