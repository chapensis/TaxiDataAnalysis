package com.yangchang.TaxiDataAnalysis.dao;

import com.yangchang.TaxiDataAnalysis.bean.po.RoadInfoPO;
import com.yangchang.TaxiDataAnalysis.bean.po.RoadListInfoPO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RoadInfoDao {
    List<RoadInfoPO> getRoadList(RoadInfoPO roadinfo);

    List<RoadListInfoPO> getRoadListInfo(RoadInfoPO roadinfo);
}
