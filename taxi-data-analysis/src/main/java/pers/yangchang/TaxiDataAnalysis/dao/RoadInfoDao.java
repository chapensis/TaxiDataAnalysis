package pers.yangchang.TaxiDataAnalysis.dao;

import pers.yangchang.TaxiDataAnalysis.bean.po.RoadInfoPO;
import pers.yangchang.TaxiDataAnalysis.bean.po.RoadListInfoPO;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.cache.decorators.FifoCache;

import java.util.List;

@CacheNamespace(
        eviction = FifoCache.class,
        flushInterval = 60000,
        size = 512,
        readWrite = true
)
@Mapper
public interface RoadInfoDao {
    List<RoadInfoPO> getRoadList(RoadInfoPO roadInfo);

    List<RoadListInfoPO> getRoadListInfo(RoadInfoPO roadInfo);

    int add(RoadInfoPO roadInfo);

    int delete(RoadInfoPO roadInfo);

    int update(RoadInfoPO roadInfo);
}
