package com.yangchang.TaxiDataAnalysis.controller;

import com.yangchang.TaxiDataAnalysis.bean.vo.ResultVO;
import com.yangchang.TaxiDataAnalysis.bean.vo.RoadInfoVO;
import com.yangchang.TaxiDataAnalysis.service.RoadService;
import com.yangchang.TaxiDataAnalysis.tools.GeneralUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/service/road")
public class RoadController {
    @Autowired
    private RoadService roadService;

    /**
     * 获得路段信息
     *
     * @param roadInfoVO  分页页码
     * @param roadInfoVO 每页数量
     * @return
     */
    @PostMapping("list")
    public ResultVO getRoadList(@RequestBody RoadInfoVO roadInfoVO) {
        log.info("RoadController getRoadList: " + roadInfoVO);
        List list = roadService.getRoadList(roadInfoVO);
        ResultVO result = GeneralUtil.success(list);
        return result;
    }

    @PostMapping("total")
    public ResultVO getTotalRoadNum(@RequestBody RoadInfoVO roadInfoVO) {
        log.info("RoadController getTotalRoadNum: " + roadInfoVO);
        List roadListInfoVOs = roadService.getTotalRoadNum(roadInfoVO);
        ResultVO result = GeneralUtil.success(roadListInfoVOs);
        return result;
    }
}
