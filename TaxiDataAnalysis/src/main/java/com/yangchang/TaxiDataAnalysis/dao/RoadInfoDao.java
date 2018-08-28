package com.yangchang.TaxiDataAnalysis.dao;

import com.yangchang.TaxiDataAnalysis.bean.po.RoadInfoPO;

import java.util.List;

public interface RoadInfoDao {
    List<RoadInfoPO> getRoadInfos();
}
