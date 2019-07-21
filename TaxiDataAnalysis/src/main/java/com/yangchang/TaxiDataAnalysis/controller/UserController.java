package com.yangchang.TaxiDataAnalysis.controller;

import com.yangchang.TaxiDataAnalysis.bean.vo.ResultVO;
import com.yangchang.TaxiDataAnalysis.bean.vo.UserVO;
import com.yangchang.TaxiDataAnalysis.service.UserService;
import com.yangchang.TaxiDataAnalysis.tools.GeneralUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(value = "/service/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 登陆成功，需要把用户的token信息存到header中，
     *
     * @param user
     * @return
     */
    @PostMapping("login")
    public ResultVO login(@RequestBody UserVO user) {
        UserVO resultUser = userService.login();
        return GeneralUtil.success(resultUser, "用户登录成功");
    }

    @GetMapping("info")
    public ResultVO info() {
        UserVO resultUser = userService.getUserInfo();
        return GeneralUtil.success(resultUser, "查询用户信息成功");
    }
}
