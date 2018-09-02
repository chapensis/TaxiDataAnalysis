package com.yangchang.TaxiDataAnalysis.bean.vo;

import lombok.Data;

import java.util.List;

@Data
public class UserVO {

    private String username;

    private String password;

    private List<String> roles;

    private String token;

    private String introduction;

    private String avatar;

    private String name;
}
