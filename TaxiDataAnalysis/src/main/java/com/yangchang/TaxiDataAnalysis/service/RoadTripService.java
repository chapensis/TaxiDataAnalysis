package com.yangchang.TaxiDataAnalysis.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yangchang.TaxiDataAnalysis.bean.po.RoadTripInfoPO;
import com.yangchang.TaxiDataAnalysis.bean.vo.RoadTripInfoVO;
import com.yangchang.TaxiDataAnalysis.dao.RoadTripInfoDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class RoadTripService {
    @Autowired
    private RoadTripInfoDao roadTripInfoDao;

    /**
     * 根据分页条件获得路段信息
     *
     * @return List<RoadTripInfoVO>
     */
    public List<RoadTripInfoVO> getRoadSeekingTripList(RoadTripInfoVO roadTripInfoVO) {
        RoadTripInfoPO roadTripInfoPO = roadTripInfoVO.toPO();

        PageInfo<RoadTripInfoPO> roadTripInfoPageInfo = getRoadSeekingTripByPageHelper(roadTripInfoPO);
        List<RoadTripInfoPO> roadTripInfoPOs = roadTripInfoPageInfo.getList();
        List<RoadTripInfoVO> roadTripInfoVOs = roadTripInfoPOs.stream().map(x -> RoadTripInfoVO.parseVO(x)).collect(Collectors.toList());
        return roadTripInfoVOs;
    }

    /**
     * 根据分页条件获得路段信息
     *
     * @return List<RoadTripInfoVO>
     */
    public List<RoadTripInfoVO> getRoadDrivingTripList(RoadTripInfoVO roadTripInfoVO) {
        RoadTripInfoPO roadTripInfoPO = roadTripInfoVO.toPO();

        PageInfo<RoadTripInfoPO> roadTripInfoPageInfo = getRoadDrivingTripByPageHelper(roadTripInfoPO);
        List<RoadTripInfoPO> roadTripInfoPOs = roadTripInfoPageInfo.getList();
        List<RoadTripInfoVO> roadTripInfoVOs = roadTripInfoPOs.stream().map(x -> RoadTripInfoVO.parseVO(x)).collect(Collectors.toList());
        return roadTripInfoVOs;
    }

    /**
     * 根据条件查询路段集合的信息，比例：集合的总数量等
     *
     * @param roadTripInfoVO
     * @return
     */
    public Integer getRoadSeekingTripListNum(RoadTripInfoVO roadTripInfoVO) {
        RoadTripInfoPO roadTripInfoPO = roadTripInfoVO.toPO();

        Integer num = roadTripInfoDao.getRoadSeekingTripListNum(roadTripInfoPO);
        return num;
    }

    /**
     * 根据条件查询路段集合的信息，比例：集合的总数量等
     *
     * @param roadTripInfoVO
     * @return
     */
    public Integer getRoadDrivingTripListNum(RoadTripInfoVO roadTripInfoVO) {
        RoadTripInfoPO roadTripInfoPO = roadTripInfoVO.toPO();

        Integer num = roadTripInfoDao.getRoadDrivingTripListNum(roadTripInfoPO);
        return num;
    }

    /**
     * 这个方法中用到了我们开头配置依赖的分页插件pagehelper
     * 很简单，只需要在service层传入参数，然后将参数传递给一个插件的一个静态方法即可；
     * pageNum 开始页数
     * pageSize 每页显示的数据条数
     */
    private PageInfo<RoadTripInfoPO> getRoadSeekingTripByPageHelper(RoadTripInfoPO roadTripInfoPO) {
        //将参数传给这个方法就可以实现物理分页了，非常简单。
        PageHelper.startPage(roadTripInfoPO.getPagination().getPageNum(),
                roadTripInfoPO.getPagination().getPageSize());
        // 先选择出指定的数据
        List<RoadTripInfoPO> roadTripInfoPOs = roadTripInfoDao.getRoadSeekingTripList(roadTripInfoPO);
        log.info("seleced RoadSeekingTripInfoPOs size: " + roadTripInfoPOs.size());
        PageInfo result = new PageInfo(roadTripInfoPOs);
        return result;
    }

    /**
     * 这个方法中用到了我们开头配置依赖的分页插件pagehelper
     * 很简单，只需要在service层传入参数，然后将参数传递给一个插件的一个静态方法即可；
     * pageNum 开始页数
     * pageSize 每页显示的数据条数
     */
    private PageInfo<RoadTripInfoPO> getRoadDrivingTripByPageHelper(RoadTripInfoPO roadTripInfoPO) {
        //将参数传给这个方法就可以实现物理分页了，非常简单。
        PageHelper.startPage(roadTripInfoPO.getPagination().getPageNum(),
                roadTripInfoPO.getPagination().getPageSize());
        // 先选择出指定的数据
        List<RoadTripInfoPO> roadTripInfoPOs = roadTripInfoDao.getRoadDrivingTripList(roadTripInfoPO);
        log.info("seleced RoadDrivingTripInfoPOs size: " + roadTripInfoPOs.size());
        PageInfo result = new PageInfo(roadTripInfoPOs);
        return result;
    }

}
