package pers.yangchang.TaxiDataAnalysis.controller;

import pers.yangchang.TaxiDataAnalysis.bean.vo.ResultVO;
import pers.yangchang.TaxiDataAnalysis.bean.vo.RoadTripInfoVO;
import pers.yangchang.TaxiDataAnalysis.service.RoadTripService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * @author yangchang
 */
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
    public ResultVO listRoadSeekingTrips(@RequestBody RoadTripInfoVO roadTripInfoVO) {
        List list = roadTripService.listRoadSeekingTrips(roadTripInfoVO);
        ResultVO result = ResultVO.success(list);
        return result;
    }

    /**
     * 获得路段载客trip信息
     *
     * @param roadTripInfoVO 查询信息
     * @return ResultVO
     */
    @PostMapping("dlist")
    public ResultVO listRoadDrivingTrips(@RequestBody RoadTripInfoVO roadTripInfoVO) {
        List list = roadTripService.listRoadDrivingTrips(roadTripInfoVO);
        ResultVO result = ResultVO.success(list);
        return result;
    }
}
