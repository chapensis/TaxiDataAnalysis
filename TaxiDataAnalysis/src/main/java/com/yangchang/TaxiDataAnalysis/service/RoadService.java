package com.yangchang.TaxiDataAnalysis.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yangchang.TaxiDataAnalysis.bean.po.RoadInfoPO;
import com.yangchang.TaxiDataAnalysis.bean.po.RoadListInfoPO;
import com.yangchang.TaxiDataAnalysis.bean.vo.RoadInfoVO;
import com.yangchang.TaxiDataAnalysis.bean.vo.RoadListInfoVO;
import com.yangchang.TaxiDataAnalysis.dao.RoadInfoDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class RoadService {
    @Autowired
    private RoadInfoDao roadInfoDao;//这里会报错，但是并不会影响

    /**
     * 根据分页条件获得路段信息
     *
     * @return List<RoadInfoVO>
     */
    public List<RoadInfoVO> getRoadList(RoadInfoVO roadInfoVO) {
        RoadInfoPO roadInfoPO = roadInfoVO.toPO();

        PageInfo<RoadInfoPO> roadInfoPOPageInfo = getRoadInfosByPageHelper(roadInfoPO);
        List<RoadInfoPO> roadInfoPOs = roadInfoPOPageInfo.getList();
        List<RoadInfoVO> roadInfoVOs = roadInfoPOs.stream().map(x -> RoadInfoVO.parseVO(x)).collect(Collectors.toList());
        return roadInfoVOs;
    }

    /**
     * 根据条件查询路段集合的信息，比例：集合的总数量等
     *
     * @param roadInfoVO
     * @return
     */
    public List<RoadListInfoVO> getTotalRoadNum(RoadInfoVO roadInfoVO) {
        RoadInfoPO roadInfoPO = roadInfoVO.toPO();

        List<RoadListInfoPO> roadListInfoPOS = roadInfoDao.getRoadListInfo(roadInfoPO);
        List<RoadListInfoVO> roadListInfoVOs = roadListInfoPOS.stream().map(x -> RoadListInfoVO.parseVO(x)).collect(Collectors.toList());
        return roadListInfoVOs;
    }

    /**
     * 这个方法中用到了我们开头配置依赖的分页插件pagehelper
     * 很简单，只需要在service层传入参数，然后将参数传递给一个插件的一个静态方法即可；
     * pageNum 开始页数
     * pageSize 每页显示的数据条数
     */
    private PageInfo<RoadInfoPO> getRoadInfosByPageHelper(RoadInfoPO roadInfo) {
        //将参数传给这个方法就可以实现物理分页了，非常简单。
        PageHelper.startPage(roadInfo.getPagination().getPageNum(),
                roadInfo.getPagination().getPageSize());
        // 先选择出指定的数据
        List<RoadInfoPO> roadInfoPOs = roadInfoDao.getRoadList(roadInfo);
        log.info("seleced RoadInfoPOs size: " + roadInfoPOs.size());
        PageInfo result = new PageInfo(roadInfoPOs);
        return result;
    }

}
