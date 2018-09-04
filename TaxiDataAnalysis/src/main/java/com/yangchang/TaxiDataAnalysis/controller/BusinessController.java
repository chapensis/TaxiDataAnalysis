package com.yangchang.TaxiDataAnalysis.controller;

import com.yangchang.TaxiDataAnalysis.bean.vo.BusinessVO;
import com.yangchang.TaxiDataAnalysis.bean.vo.ResultVO;
import com.yangchang.TaxiDataAnalysis.service.BusinessService;
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
@RequestMapping(value = "/service/business")
public class BusinessController {
    @Autowired
    private BusinessService businessService;

    /**
     * 获得交易信息
     *
     * @param businessVO 分页页码
     * @return ResultVO
     */
    @PostMapping("list")
    public ResultVO getBusinessList(@RequestBody BusinessVO businessVO) {
        List list = businessService.getBusinessList(businessVO);
        ResultVO result = GeneralUtil.success(list);
        return result;
    }

    /**
     * 获得交易总数量
     *
     * @param businessVO
     * @return
     */
    @PostMapping("total")
    public ResultVO getBusinessListNum(@RequestBody BusinessVO businessVO) {
        Integer num = businessService.getBusinessListNum(businessVO);
        ResultVO result = GeneralUtil.success(new ArrayList() {
            {
                add(num);
            }
        });
        return result;
    }
}
