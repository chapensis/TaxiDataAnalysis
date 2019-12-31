package pers.yangchang.TaxiDataAnalysis.dao;

import pers.yangchang.TaxiDataAnalysis.bean.po.RoadTripInfoPO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RoadTripInfoDao {
    List<RoadTripInfoPO> getRoadSeekingTripList(RoadTripInfoPO roadTripInfo);

    List<RoadTripInfoPO> getRoadDrivingTripList(RoadTripInfoPO roadTripInfo);

    Integer getRoadSeekingTripListNum(RoadTripInfoPO roadTripInfo);

    Integer getRoadDrivingTripListNum(RoadTripInfoPO roadTripInfo);
}
