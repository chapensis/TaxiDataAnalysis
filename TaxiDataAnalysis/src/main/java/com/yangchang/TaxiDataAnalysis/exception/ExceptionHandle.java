package com.yangchang.TaxiDataAnalysis.exception;

import com.yangchang.TaxiDataAnalysis.bean.vo.ResultVO;
import com.yangchang.TaxiDataAnalysis.tools.GeneralUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@ControllerAdvice
@RestController
public class ExceptionHandle {
    /**
     * handle any Exception
     *
     * @param e exception
     * @return
     */
    @ExceptionHandler(Exception.class)
    public ResultVO handleException(Exception e) {
        log.error("[Catch Exception]:" + e.getMessage());
        return GeneralUtil.fail(1, "操作失败，详情请查看日志");
    }
}
