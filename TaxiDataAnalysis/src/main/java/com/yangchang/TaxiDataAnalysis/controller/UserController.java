package com.yangchang.TaxiDataAnalysis.controller;

import com.yangchang.TaxiDataAnalysis.bean.vo.ResultVO;
import com.yangchang.TaxiDataAnalysis.bean.vo.UserVO;
import com.yangchang.TaxiDataAnalysis.service.UserService;
import com.yangchang.TaxiDataAnalysis.tools.GeneralUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/service/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("login")
    public UserVO login(@RequestBody UserVO user) {
        UserVO resultUser = userService.getUserInfo();
        return resultUser;
    }

    @GetMapping("info")
    public UserVO info(@RequestParam("user") String user) {
        UserVO resultUser = userService.getUserInfo();
        return resultUser;
    }


}
