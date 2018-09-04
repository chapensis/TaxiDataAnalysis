package com.yangchang.TaxiDataAnalysis.Aspect;

import com.yangchang.TaxiDataAnalysis.bean.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Slf4j
@Component
@Aspect
public class ControllerAspect {

    @Pointcut("execution(public * com.yangchang.TaxiDataAnalysis.controller.*.*(..))")
    public void controllerPointCut() {

    }

    @Before("controllerPointCut()")
    public void logInputArgs(JoinPoint joinPoint) {

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        String controllerName = joinPoint.getSignature().getDeclaringType().getName();
        HttpServletRequest request = attributes.getRequest();
        String simpleControllerName = controllerName.substring(controllerName.lastIndexOf(".") + 1, controllerName.length());
        log.info("<<<<<<<<<<<< " + simpleControllerName + " Start >>>>>>>>>>>");
        log.info("URL: " + request.getRequestURI());
        Object[] args = joinPoint.getArgs();
        log.info("Args: " + Arrays.toString(args));
    }

    @AfterReturning(returning = "resultVO", pointcut = "controllerPointCut()")
    public void logOutput(ResultVO resultVO) {
        log.info("Return Data: " + resultVO);
    }

    @AfterThrowing(value = "controllerPointCut()", throwing = "throwable")
    public void logControllerException(JoinPoint joinPoint, Throwable throwable) {
        String clazzName = joinPoint.getSignature().getDeclaringType().getSimpleName();
        Object[] args = joinPoint.getArgs();
        String method = joinPoint.getSignature().getName();
        log.error("异常类名：" + clazzName);
        log.error("异常方法名：" + method);
        log.error("异常参数列表：" + Arrays.toString(args));
        log.error("异常详情：" + throwable.toString());
        StackTraceElement[] elements = throwable.getStackTrace();
        StackTraceElement element = elements[0];
        log.error("异常方法名：" + element.getMethodName() + " 异常位置：" + element.getLineNumber());
    }
}
