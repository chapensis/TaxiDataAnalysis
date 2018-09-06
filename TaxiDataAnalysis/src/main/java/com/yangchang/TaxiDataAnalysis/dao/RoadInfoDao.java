package com.yangchang.TaxiDataAnalysis.dao;

import com.yangchang.TaxiDataAnalysis.bean.po.RoadInfoPO;
import com.yangchang.TaxiDataAnalysis.bean.po.RoadListInfoPO;
import com.yangchang.TaxiDataAnalysis.bean.vo.RoadInfoVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RoadInfoDao {
    List<RoadInfoPO> getRoadList(RoadInfoPO roadInfo);

    List<RoadListInfoPO> getRoadListInfo(RoadInfoPO roadInfo);

    int add(RoadInfoPO roadInfo);

    int delete(RoadInfoPO roadInfo);

    int update(RoadInfoPO roadInfo);
}
