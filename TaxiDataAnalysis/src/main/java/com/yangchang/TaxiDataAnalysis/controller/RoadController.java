package com.yangchang.TaxiDataAnalysis.controller;

import com.yangchang.TaxiDataAnalysis.service.RoadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/road")
public class RoadController {
    @Autowired
    private RoadService roadService;

    @GetMapping("helloworld")
    public String helloworld() {
        return roadService.getRoadInfos().toString();
    }

    /**
     * 获得路段信息
     * @param pageNum 分页页码
     * @param pageSize 每页数量
     * @return
     */
    @GetMapping("info")
    public String getRoadInfos(@RequestParam("pageNum") int pageNum,
                               @RequestParam("pageSize") int pageSize) {
        return roadService.getRoadInfos(pageNum, pageSize).toString();
    }
}
