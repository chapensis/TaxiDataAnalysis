package pers.yangchang.TaxiDataAnalysis.Aspect;

import pers.yangchang.TaxiDataAnalysis.bean.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * @author yangchang
 * Controller层切面
 */
@Slf4j
@Aspect
@Component
public class ControllerAspect {

    /**
     * 切点，里面可以用execution指定切哪个方法，
     * 也可以用annotation指定切添加了哪个注解声明的方法
     * 也可以用within指定切哪些类
     */
    @Pointcut("execution(public * com.yangchang.TaxiDataAnalysis.controller.*.*(..))")
    public void controllerPointCut() {

    }

    /**
     * 打印入参信息
     * TODO 这个拦截器的优先级还低于参数校验的优先级
     *
     * @param joinPoint
     */
    @Before("controllerPointCut()")
    public void logInputArgs(JoinPoint joinPoint) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        String controllerName = joinPoint.getSignature().getDeclaringType().getName();
        HttpServletRequest request = attributes.getRequest();
        String simpleControllerName = controllerName.substring(controllerName.lastIndexOf(".") + 1);
        log.info("<<<<<<<<<<<< " + simpleControllerName + "Controller Start DEBUGGING>>>>>>>>>>>");
        log.info("URL: " + request.getRequestURI());
        log.info("[控制器名]: " + simpleControllerName);
        String methodName = joinPoint.getSignature().getName();
        log.info("[函数名]: " + methodName);
        Object[] args = joinPoint.getArgs();
        log.info("[传入参数]: " + Arrays.toString(args));
    }

    /**
     * 打印返回消息
     *
     * @param resultVO
     */
    @AfterReturning(returning = "resultVO", pointcut = "controllerPointCut()")
    public void logOutput(ResultVO resultVO) {
        log.info("[Controller 成功返回消息] Return Data: " + resultVO);
    }

    /**
     * 控制层异常信息打印
     *
     * @param joinPoint
     * @param throwable
     */
    @AfterThrowing(value = "controllerPointCut()", throwing = "throwable")
    public void logControllerException(JoinPoint joinPoint, Throwable throwable) {
        String clazzName = joinPoint.getSignature().getDeclaringType().getSimpleName();
        Object[] args = joinPoint.getArgs();
        String method = joinPoint.getSignature().getName();
        log.error("[控制器层出现异常]，异常类名：" + clazzName);
        log.error("异常方法名：" + method);
        log.error("异常参数列表：" + Arrays.toString(args));
        log.error("异常详情：" + throwable.toString());
        StackTraceElement[] elements = throwable.getStackTrace();
        StackTraceElement element = elements[0];
        log.error("[控制器层异常位置]，异常方法名：" + element.getMethodName() + " 异常位置：" + element.getLineNumber());
    }
}
