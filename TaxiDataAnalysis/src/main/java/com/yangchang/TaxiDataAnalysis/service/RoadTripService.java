package com.yangchang.TaxiDataAnalysis.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yangchang.TaxiDataAnalysis.bean.Pagination;
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
    public List<RoadTripInfoVO> listRoadSeekingTrips(RoadTripInfoVO roadTripInfoVO) {
        RoadTripInfoPO roadTripInfoPO = roadTripInfoVO.toPO();

        PageInfo<RoadTripInfoPO> roadTripPageInfo = getRoadSeekingTripByPageHelper(roadTripInfoPO);
        List<RoadTripInfoPO> roadTripInfoPOs = roadTripPageInfo.getList();

        Long total = roadTripPageInfo.getTotal();
        Pagination pagination = new Pagination();
        pagination.setTotal(total);

        List<RoadTripInfoVO> roadTripInfoVOs = roadTripInfoPOs.stream().map(x -> {
            RoadTripInfoVO roadTripResultInfoVO = RoadTripInfoVO.parseVO(x);
            roadTripResultInfoVO.setPagination(pagination);
            return roadTripResultInfoVO;
        }).collect(Collectors.toList());
        return roadTripInfoVOs;
    }

    /**
     * 根据分页条件获得路段信息
     *
     * @return List<RoadTripInfoVO>
     */
    public List<RoadTripInfoVO> listRoadDrivingTrips(RoadTripInfoVO roadTripInfoVO) {
        RoadTripInfoPO roadTripInfoPO = roadTripInfoVO.toPO();

        PageInfo<RoadTripInfoPO> roadTripPageInfo = getRoadDrivingTripByPageHelper(roadTripInfoPO);

        Long total = roadTripPageInfo.getTotal();
        Pagination pagination = new Pagination();
        pagination.setTotal(total);

        List<RoadTripInfoPO> roadTripInfoPOs = roadTripPageInfo.getList();
        List<RoadTripInfoVO> roadTripInfoVOs = roadTripInfoPOs.stream().map(x -> {
            RoadTripInfoVO roadTripResultInfoVO = RoadTripInfoVO.parseVO(x);
            roadTripResultInfoVO.setPagination(pagination);
            return roadTripResultInfoVO;
        }).collect(Collectors.toList());
        return roadTripInfoVOs;
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
