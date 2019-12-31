package pers.yangchang.TaxiDataAnalysis.controller;

import pers.yangchang.TaxiDataAnalysis.bean.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.JedisPool;

@Slf4j
@RestController
@RequestMapping(value = "/service/test")
public class TestController {

    @Autowired
    private JedisPool jedisPool;

    /**
     * 测试jedis是否注入成功
     *
     * @return
     */
    @PostMapping("jedis")
    public ResultVO jedis() {
        log.info("查看jedisPool是否为空：" + jedisPool);
        return ResultVO.success("success", "用户操作成功");
    }
}
