package pers.yangchang.TaxiDataAnalysis.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import pers.yangchang.TaxiDataAnalysis.bean.Pagination;
import pers.yangchang.TaxiDataAnalysis.bean.po.RoadInfoPO;
import pers.yangchang.TaxiDataAnalysis.bean.vo.RoadInfoVO;
import pers.yangchang.TaxiDataAnalysis.dao.RoadInfoDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class RoadService {
    @Autowired
    private RoadInfoDao roadInfoDao;//这里会报错，但是并不会影响

    /**
     * 根据分页条件获得路段信息
     *
     * @return List<RoadInfoVO>
     */
    public List<RoadInfoVO> listRoads(RoadInfoVO roadInfoVO) {
        RoadInfoPO roadInfoPO = roadInfoVO.toPO();

        PageInfo<RoadInfoPO> roadPageInfo = getRoadInfosByPageHelper(roadInfoPO);

        Long total = roadPageInfo.getTotal();
        Pagination pagination = new Pagination();
        pagination.setTotal(total);

        List<RoadInfoPO> roadInfoPOs = roadPageInfo.getList();
        List<RoadInfoVO> roadInfoVOs = roadInfoPOs.stream().map(x -> {
            RoadInfoVO roadInfoResultVO = RoadInfoVO.parseVO(x);
            roadInfoResultVO.setPagination(pagination);
            return roadInfoResultVO;
        }).collect(Collectors.toList());
        return roadInfoVOs;
    }

    /**
     * 添加新的路段
     *
     * @param roadInfoVO
     * @return
     */
    public RoadInfoVO addRoad(RoadInfoVO roadInfoVO) {
        RoadInfoPO roadInfoPO = roadInfoVO.toPO();

        roadInfoDao.add(roadInfoPO);
        return RoadInfoVO.parseVO(roadInfoPO);
    }

    /**
     * delete road
     *
     * @param roadInfoVO
     * @return
     */
    public int deleteRoad(RoadInfoVO roadInfoVO) {
        RoadInfoPO roadInfoPO = roadInfoVO.toPO();
        int result = roadInfoDao.delete(roadInfoPO);
        return result;
    }

    /**
     * delete road
     *
     * @param roadInfoVO
     * @return
     */
    public int updateRoad(RoadInfoVO roadInfoVO) {
        RoadInfoPO roadInfoPO = roadInfoVO.toPO();
        int result = roadInfoDao.update(roadInfoPO);
        return result;
    }


    /**
     * 这个方法中用到了我们开头配置依赖的分页插件pagehelper
     * 很简单，只需要在service层传入参数，然后将参数传递给一个插件的一个静态方法即可；
     * pageNum 开始页数
     * pageSize 每页显示的数据条数
     */
    private PageInfo<RoadInfoPO> getRoadInfosByPageHelper(RoadInfoPO roadInfo) {
        //将参数传给这个方法就可以实现物理分页了，非常简单。
        PageHelper.startPage(roadInfo.getPagination().getPageNum(),
                roadInfo.getPagination().getPageSize());
        // 先选择出指定的数据
        List<RoadInfoPO> roadInfoPOs = roadInfoDao.getRoadList(roadInfo);
        log.info("seleced RoadInfoPOs size: " + roadInfoPOs.size());
        PageInfo result = new PageInfo(roadInfoPOs);
        return result;
    }

}
