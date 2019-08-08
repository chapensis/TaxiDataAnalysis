package com.yangchang.TaxiDataAnalysis.controller;

import com.yangchang.TaxiDataAnalysis.bean.vo.ResultVO;
import com.yangchang.TaxiDataAnalysis.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yangchang
 * 主页
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
        ResultVO result = ResultVO.success(visitCount);
        return result;
    }
}
