package com.yangchang.TaxiDataAnalysis.bean.vo;

import lombok.Data;

import java.util.List;

/**
 * @author yangchang
 * 返回给前台的信息
 */
@Data
public class ResultVO {
    /**
     * 返回码，0-成功 其他-错误
     */
    private Integer code;

    /**
     * 返回信息
     */
    private String message;

    /**
     * 返回对象
     */
    private Object data;
}
