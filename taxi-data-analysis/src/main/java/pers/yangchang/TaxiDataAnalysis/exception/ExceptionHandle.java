package pers.yangchang.TaxiDataAnalysis.exception;

import pers.yangchang.TaxiDataAnalysis.bean.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.ValidationException;
import java.util.List;

/**
 * @author yangchang
 * 统一异常处理
 */
@Slf4j
@ControllerAdvice
@RestController
public class ExceptionHandle {

    /**
     * 处理参数验证异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResultVO handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error("[Catch MethodArgumentNotValidException]:" + e.getMessage());
        List<ObjectError> errors = e.getBindingResult().getAllErrors();
        // 一次性可以返回所有错误信息
        StringBuilder errMsg = new StringBuilder();
        for (ObjectError error : errors) {
            log.error(error.getDefaultMessage());
            errMsg.append(error.getDefaultMessage()).append(" ");
        }
        return ResultVO.fail(1, errMsg.toString());
    }

    /**
     * 处理验证异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(ValidationException.class)
    public ResultVO handleValidationException(ValidationException e) {
        log.error("[Catch ValidationException]:" + e.getMessage());
        return ResultVO.fail(1, "操作失败，详情请查看日志");
    }

    /**
     * 处理自定义异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(CustomException.class)
    public ResultVO handleCustomException(CustomException e) {
        log.error("[Catch CustomException]:" + e.getMessage());
        Integer errCode = e.getErrCode();
        String errMsg = e.getErrMsg();

        return ResultVO.fail(errCode, errMsg);
    }

    /**
     * 处理参数验证异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(ServletRequestBindingException.class)
    public ResultVO handleCustomException(ServletRequestBindingException e) {
        log.error("[Catch ServletRequestBindingException]:" + e.getMessage());
        Integer errCode = 1;
        String errMsg = e.getMessage();

        return ResultVO.fail(errCode, errMsg);
    }

    /**
     * 处理所有异常
     *
     * @param e exception
     * @return
     */
    @ExceptionHandler(Exception.class)
    public ResultVO handleException(Exception e) {
        log.error("[Catch Exception]:" + e.getMessage());
        log.error("e.getClass().getSimpleName():" + e.getClass().getSimpleName());
        return ResultVO.fail(1, "操作失败，详情请查看日志");
    }
}
