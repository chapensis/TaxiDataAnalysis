package com.yangchang.TaxiDataAnalysis.controller;

import com.yangchang.TaxiDataAnalysis.bean.vo.ResultVO;
import com.yangchang.TaxiDataAnalysis.bean.vo.RoadInfoVO;
import com.yangchang.TaxiDataAnalysis.service.RoadService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yangchang
 */
@Slf4j
@RestController
@RequestMapping(value = "/service/road")
public class RoadController {
    @Autowired
    private RoadService roadService;

    /**
     * 获得路段信息
     *
     * @param roadInfoVO 分页页码
     * @param roadInfoVO 每页数量
     * @return
     */
    @PostMapping("list")
    public ResultVO listRoads(@RequestBody RoadInfoVO roadInfoVO) {
        List list = roadService.listRoads(roadInfoVO);
        ResultVO result = ResultVO.success(list);
        return result;
    }

    /**
     * 添加路段信息
     *
     * @param roadInfoVO
     * @return
     */
    @PostMapping("add")
    public ResultVO addRoad(@RequestBody RoadInfoVO roadInfoVO) {
        RoadInfoVO resultRoad = roadService.addRoad(roadInfoVO);
        ResultVO result = ResultVO.success(new ArrayList() {{
            add(resultRoad);
        }}, "添加路段成功");
        return result;
    }

    /**
     * 删除路段信息
     *
     * @param roadInfoVO
     * @return
     */
    @PostMapping("delete")
    public ResultVO deleteRoad(@RequestBody RoadInfoVO roadInfoVO) {
        int resultLine = roadService.deleteRoad(roadInfoVO);
        ResultVO result = ResultVO.success(new ArrayList() {{
            add(resultLine);
        }}, "删除路段成功");
        return result;
    }

    /**
     * update路段信息
     *
     * @param roadInfoVO
     * @return
     */
    @PostMapping("update")
    public ResultVO updateRoad(@RequestBody RoadInfoVO roadInfoVO) {
        int resultLine = roadService.updateRoad(roadInfoVO);
        ResultVO result = ResultVO.success(new ArrayList() {{
            add(resultLine);
        }}, "更新路段成功");
        return result;
    }
}
