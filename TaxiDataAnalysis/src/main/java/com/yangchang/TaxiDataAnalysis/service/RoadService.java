package com.yangchang.TaxiDataAnalysis.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yangchang.TaxiDataAnalysis.bean.po.RoadInfoPO;
import com.yangchang.TaxiDataAnalysis.bean.vo.RoadInfoVO;
import com.yangchang.TaxiDataAnalysis.dao.RoadInfoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoadService {
    @Autowired
    private RoadInfoDao roadInfoDao;//这里会报错，但是并不会影响

    /**
     * 获得所有路段信息
     * @return
     */
    public List<RoadInfoVO> getRoadInfos() {

        List<RoadInfoPO> roadInfoPOs = roadInfoDao.getRoadInfos();
        List<RoadInfoVO> roadInfoVOs = roadInfoPOs.stream().map(x -> RoadInfoVO.parseVO(x)).collect(Collectors.toList());
        return roadInfoVOs;
    }

    /**
     * 根据分页条件获得路段信息
     * @param pageNum 页码
     * @param pageSize 每页数量
     * @return List<RoadInfoVO>
     */
    public List<RoadInfoVO> getRoadInfos(int pageNum, int pageSize) {
        PageInfo<RoadInfoPO> roadInfoPOPageInfo = getRoadInfosByPageHelper(pageNum, pageSize);
        List<RoadInfoPO> roadInfoPOs = roadInfoPOPageInfo.getList();
        List<RoadInfoVO> roadInfoVOs = roadInfoPOs.stream().map(x -> RoadInfoVO.parseVO(x)).collect(Collectors.toList());
        return roadInfoVOs;
    }

    /**
     * 这个方法中用到了我们开头配置依赖的分页插件pagehelper
     * 很简单，只需要在service层传入参数，然后将参数传递给一个插件的一个静态方法即可；
     * pageNum 开始页数
     * pageSize 每页显示的数据条数
     * */
    public PageInfo<RoadInfoPO> getRoadInfosByPageHelper(int pageNum, int pageSize) {
        //将参数传给这个方法就可以实现物理分页了，非常简单。
        PageHelper.startPage(pageNum, pageSize);
        // 先选择出所有的数据
        List<RoadInfoPO> RoadInfoPOs = roadInfoDao.getRoadInfos();
        PageInfo result = new PageInfo(RoadInfoPOs);
        return result;
    }

}
