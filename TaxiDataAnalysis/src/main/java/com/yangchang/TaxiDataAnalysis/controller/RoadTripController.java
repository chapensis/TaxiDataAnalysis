package com.yangchang.TaxiDataAnalysis.controller;

import com.yangchang.TaxiDataAnalysis.bean.vo.ResultVO;
import com.yangchang.TaxiDataAnalysis.bean.vo.RoadInfoVO;
import com.yangchang.TaxiDataAnalysis.bean.vo.RoadTripInfoVO;
import com.yangchang.TaxiDataAnalysis.service.RoadService;
import com.yangchang.TaxiDataAnalysis.service.RoadTripService;
import com.yangchang.TaxiDataAnalysis.tools.GeneralUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/service/roadtrip")
public class RoadTripController {
    @Autowired
    private RoadTripService roadTripService;

    /**
     * 获得路段寻客trip信息
     *
     * @param roadTripInfoVO 查询信息
     * @return
     */
    @PostMapping("slist")
    public ResultVO getRoadSeekingTripList(@RequestBody RoadTripInfoVO roadTripInfoVO) {
        log.info("RoadTripController getRoadSeekingTripList: " + roadTripInfoVO);
        List list = roadTripService.getRoadSeekingTripList(roadTripInfoVO);
        ResultVO result = GeneralUtil.success(list);
        return result;
    }

    /**
     * 获得路段载客trip信息
     *
     * @param roadTripInfoVO 查询信息
     * @return ResultVO
     */
    @PostMapping("dlist")
    public ResultVO getRoadDrivingTripList(@RequestBody RoadTripInfoVO roadTripInfoVO) {
        log.info("RoadTripController getRoadDrivingTripList: " + roadTripInfoVO);
        List list = roadTripService.getRoadDrivingTripList(roadTripInfoVO);
        ResultVO result = GeneralUtil.success(list);
        return result;
    }

    /**
     * 获得路段寻客trip信息总数
     *
     * @param roadTripInfoVO
     * @return ResultVO
     */
    @PostMapping("stotal")
    public ResultVO getRoadSeekingTripListNum(@RequestBody RoadTripInfoVO roadTripInfoVO) {
        log.info("RoadController getTotalRoadNum: " + roadTripInfoVO);
        Integer num = roadTripService.getRoadSeekingTripListNum(roadTripInfoVO);
        ResultVO result = GeneralUtil.success(new ArrayList() {
            {
                add(num);
            }
        });
        return result;
    }

    /**
     * 获得路段载客trip信息总数
     *
     * @param roadTripInfoVO
     * @return
     */
    @PostMapping("dtotal")
    public ResultVO getRoadDrivingTripListNum(@RequestBody RoadTripInfoVO roadTripInfoVO) {
        log.info("RoadController getTotalRoadNum: " + roadTripInfoVO);
        Integer num = roadTripService.getRoadDrivingTripListNum(roadTripInfoVO);
        ResultVO result = GeneralUtil.success(new ArrayList() {
            {
                add(num);
            }
        });
        return result;
    }
}
