package com.yangchang.TaxiDataAnalysis.bean.vo;

import lombok.Data;

import java.util.List;

@Data
public class ResultVO {
    private Integer code;
    private String message;
    private List<Object> data;
}
