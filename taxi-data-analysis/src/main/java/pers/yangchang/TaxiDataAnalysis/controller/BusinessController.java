package pers.yangchang.TaxiDataAnalysis.controller;

import pers.yangchang.TaxiDataAnalysis.bean.vo.BusinessVO;
import pers.yangchang.TaxiDataAnalysis.bean.vo.ResultVO;
import pers.yangchang.TaxiDataAnalysis.service.BusinessService;
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
    public ResultVO listBusiness(@RequestBody BusinessVO businessVO) {
        List list = businessService.listBusiness(businessVO);
        ResultVO result = ResultVO.success(list);
        return result;
    }
}
