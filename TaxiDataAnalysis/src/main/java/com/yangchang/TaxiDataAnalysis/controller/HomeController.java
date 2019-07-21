package com.yangchang.TaxiDataAnalysis.controller;

import com.yangchang.TaxiDataAnalysis.bean.vo.ResultVO;
import com.yangchang.TaxiDataAnalysis.bean.vo.RoadInfoVO;
import com.yangchang.TaxiDataAnalysis.service.RoadService;
import com.yangchang.TaxiDataAnalysis.service.UserService;
import com.yangchang.TaxiDataAnalysis.tools.GeneralUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yangchang
 */
@Slf4j
@RestController
@RequestMapping(value = "/service/home")
public class HomeController {

    @Autowired
    private UserService userService;

    /**
     * 获得路段信息
     *
     * @return
     */
    @PostMapping("visit")
    public ResultVO getVisitCount() {
        Integer visitCount = userService.getVisitCount();
        ResultVO result = GeneralUtil.success(visitCount);
        return result;
    }
}
