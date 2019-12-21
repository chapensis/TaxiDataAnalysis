package pers.yangchang.TaxiDataAnalysis.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import pers.yangchang.TaxiDataAnalysis.bean.Pagination;
import pers.yangchang.TaxiDataAnalysis.bean.po.BusinessPO;
import pers.yangchang.TaxiDataAnalysis.bean.vo.BusinessVO;
import pers.yangchang.TaxiDataAnalysis.dao.BusinessDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class BusinessService {
    @Autowired
    private BusinessDao businessDao;

    /**
     * 根据分页条件获得交易信息
     *
     * @return List<BusinessVO>
     */
    public List<BusinessVO> listBusiness(BusinessVO businessVO) {
        BusinessPO businessPO = businessVO.toPO();

        PageInfo<BusinessPO> businessPageInfo = getBusinessListByPageHelper(businessPO);

        Long total = businessPageInfo.getTotal();
        Pagination pagination = new Pagination();
        pagination.setTotal(total);

        List<BusinessPO> businessPOs = businessPageInfo.getList();
        List<BusinessVO> businessVOs = businessPOs.stream().map(x -> {
            BusinessVO businessReusltVO = BusinessVO.parseVO(x);
            businessReusltVO.setPagination(pagination);
            return businessReusltVO;
        }).collect(Collectors.toList());
        return businessVOs;
    }
    
    /**
     * 这个方法中用到了我们开头配置依赖的分页插件pagehelper
     * 很简单，只需要在service层传入参数，然后将参数传递给一个插件的一个静态方法即可；
     * pageNum 开始页数
     * pageSize 每页显示的数据条数
     */
    private PageInfo<BusinessPO> getBusinessListByPageHelper(BusinessPO businessPO) {
        //将参数传给这个方法就可以实现物理分页了，非常简单。
        PageHelper.startPage(businessPO.getPagination().getPageNum(),
                businessPO.getPagination().getPageSize());
        // 先选择出指定的数据
        List<BusinessPO> businessPOs = businessDao.getBusinessList(businessPO);
        log.info("seleced listBusiness size: " + businessPOs.size());
        PageInfo result = new PageInfo(businessPOs);
        return result;
    }

}
