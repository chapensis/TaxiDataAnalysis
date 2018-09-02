package com.yangchang.TaxiDataAnalysis.service;

import com.yangchang.TaxiDataAnalysis.bean.vo.UserVO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    public UserVO getUserInfo()
    {
        UserVO resultUser = new UserVO();
        List<String> roles = new ArrayList<>();
        roles.add("admin");
        resultUser.setRoles(roles);
        resultUser.setToken("admin");
        resultUser.setIntroduction("我是超级管理员杨昌");
        resultUser.setAvatar("https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        resultUser.setName("Super Amin yangchang");

        return resultUser;
    }
}
