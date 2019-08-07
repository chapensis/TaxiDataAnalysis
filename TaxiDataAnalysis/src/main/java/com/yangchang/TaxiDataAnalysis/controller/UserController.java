package com.yangchang.TaxiDataAnalysis.controller;

import com.yangchang.TaxiDataAnalysis.bean.vo.ResultVO;
import com.yangchang.TaxiDataAnalysis.bean.vo.UserVO;
import com.yangchang.TaxiDataAnalysis.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import redis.clients.jedis.JedisPool;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

/**
 * @author yangchang
 */
@Slf4j
@RestController
@RequestMapping(value = "/service/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JedisPool jedisPool;

    /**
     * 登陆成功，需要把用户的token信息存到header中，
     *
     * @param user
     * @return
     */
    @PostMapping("login")
    public ResultVO login(@Valid @RequestBody UserVO user, HttpServletRequest request) {
        log.info("查看jedisPool是否为空：" + jedisPool);
        log.info("当前用户登录信息：" + user);
        UserVO resultUser = userService.login();
        HttpSession httpSession = request.getSession();
        httpSession.setAttribute("user", resultUser);
        return ResultVO.success(resultUser, "用户登录成功");
    }

    /**
     * 查询用户信息
     * @param httpSession
     * @return
     */
    @GetMapping("info")
    public ResultVO info(HttpSession httpSession) {
        Object userSession = httpSession.getAttribute("user");
        if (userSession != null) {
            UserVO userVO = (UserVO) userSession;
            log.info("当前请求存在用户信息：" + userVO);
        } else {
            log.info("当前请求不存在用户信息");
        }
        UserVO resultUser = userService.getUserInfo();
        return ResultVO.success(resultUser, "查询用户信息成功");
    }
}
