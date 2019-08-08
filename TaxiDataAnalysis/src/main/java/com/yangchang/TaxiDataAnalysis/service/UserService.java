package com.yangchang.TaxiDataAnalysis.service;

import com.yangchang.TaxiDataAnalysis.bean.Constant;
import com.yangchang.TaxiDataAnalysis.bean.vo.UserVO;
import com.yangchang.TaxiDataAnalysis.tools.JWTUtil;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author yangchang
 */
@Slf4j
@Service
public class UserService {

    @Autowired
    public RedissonClient redissonClient;

    @Autowired
    private JedisPool jedisPool;

    /**
     * 用户登录
     * 登陆成功后保存用户token和userVO到redis中
     *
     * @return
     */
    public UserVO login(UserVO loginUserVO) {
        log.info("假装在检查用户名和密码：" + loginUserVO.getUsername() + " " + loginUserVO.getPassword());
        UserVO user = getUserInfo();
        String username = user.getUsername();
        // token: eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJjcmVhdGVUaW1lIjoxNTYzNjM0NzI1MDk3LCJleHAiOjE1NjQyMzk1MjV9._3VdB3-bdQkYDLXconrmT0EFqGigSDw0c2u1BuVF3E8
        String token = JWTUtil.sign(username, JWTUtil.SECRET);
        user.setToken(token);
        // 保存token和user到分布式redis中
        RBucket rBucket = redissonClient.getBucket(token);
        rBucket.set(user, JWTUtil.EXPIRE_TIME_MS, TimeUnit.MILLISECONDS);
        return user;
    }

    /**
     * 获得用户信息
     *
     * @return
     */
    public UserVO getUserInfo() {
        UserVO resultUser = new UserVO();
        List<String> roles = new ArrayList<>();
        roles.add("admin");
        resultUser.setRoles(roles);
        resultUser.setToken("admin");
        resultUser.setIntroduction("我是超级管理员杨昌");
        resultUser.setAvatar("https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        resultUser.setUsername("Super Amin yangchang");
        resultUser.setName("yangchang");

        return resultUser;
    }

    /**
     * 获得网站总访问次数
     *
     * @return
     */
    public Integer getVisitCount() {
        // 1、设置IP地址和端口
        Jedis jedis = jedisPool.getResource();

        // 2、保存数据
        Integer visitCount = Integer.parseInt(jedis.get(Constant.VISIT_COUNT));
        log.info("获得当前访问次数：" + visitCount);

        // 3、关闭连接
        jedis.close();

        return visitCount;
    }

    /**
     * 用户登出
     *
     * @param jwt
     */
    public void logout(String jwt) {
        RBucket rBucket = redissonClient.getBucket(jwt);
        rBucket.delete();
    }
}
