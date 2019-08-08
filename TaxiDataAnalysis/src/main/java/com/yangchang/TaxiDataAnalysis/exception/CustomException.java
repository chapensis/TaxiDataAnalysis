package com.yangchang.TaxiDataAnalysis.exception;

import lombok.Getter;

/**
 * @author yangchang
 * 自定义异常
 */
public class CustomException extends RuntimeException {
    /**
     * 错误码
     */
    @Getter
    private Integer errCode;

    /**
     * 错误原因
     */
    @Getter
    private String errMsg;

    public CustomException() {

    }

    public CustomException(Integer errCode, String errMsg) {
        super(errMsg);
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

    public CustomException(Integer errCode) {
        this.errCode = errCode;
        // TODO 从某个地方获得errCode对应的错误消息
        this.errMsg = "自定义异常错误消息";
    }

    public CustomException(Integer errCode, Throwable e) {
        super(String.valueOf(errCode), e);
        this.errCode = errCode;
        this.errMsg = e.getMessage();
    }
}
