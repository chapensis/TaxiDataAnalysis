package com.yangchang.TaxiDataAnalysis.bean.vo;

import com.yangchang.TaxiDataAnalysis.bean.Pagination;
import com.yangchang.TaxiDataAnalysis.bean.po.BusinessPO;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.Date;

@Data
public class BusinessVO {
    private Integer businessId;

    private Date stamp;

    private Integer unitId;

    private String numberPlate;

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

    /**
     * po转换成vo
     *
     * @param po po
     * @return vo
     */
    public static BusinessVO parseVO(BusinessPO po) {
        if (po == null) {
            return null;
        }
        BusinessVO vo = new BusinessVO();
        BeanUtils.copyProperties(po, vo);

        return vo;
    }

    /**
     * vo转换成po
     *
     * @return po
     */
    public BusinessPO toPO() {
        BusinessPO roadInfoPO = new BusinessPO();
        BeanUtils.copyProperties(this, roadInfoPO);

        return roadInfoPO;
    }
}
