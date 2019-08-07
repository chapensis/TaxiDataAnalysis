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

    /**
     * 成功获取数据
     *
     * @param data
     * @return
     */
    public static ResultVO success(Object data) {
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(0);
        resultVO.setData(data);
        resultVO.setMessage("获取数据成功");
        return resultVO;
    }

    /**
     * 成功获取数据
     *
     * @param data
     * @param message
     * @return
     */
    public static ResultVO success(Object data, String message) {
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(0);
        resultVO.setData(data);
        resultVO.setMessage(message);
        return resultVO;
    }

    /**
     * 获取数据失败
     *
     * @param code
     * @param message
     * @return
     */
    public static ResultVO fail(Integer code, String message) {
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(code);
        resultVO.setData(null);
        resultVO.setMessage(message);
        return resultVO;
    }
}
