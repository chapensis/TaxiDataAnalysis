package pers.yangchang.TaxiDataAnalysis.dao;

import pers.yangchang.TaxiDataAnalysis.bean.po.BusinessPO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BusinessDao {
    List<BusinessPO> getBusinessList(BusinessPO business);

    Integer getBusinessListNum(BusinessPO business);
}
