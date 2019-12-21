package pers.yangchang.TaxiDataAnalysis.Aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Slf4j
@Aspect
@Component
public class ServiceAspect {
    /**
     * 切点，里面可以用execution指定切哪个方法，
     * 也可以用annotation指定切添加了哪个注解声明的方法
     * 也可以用within指定切哪些类
     */
    @Pointcut("execution(public * com.yangchang.TaxiDataAnalysis.service.*.*(..))")
    public void servicePointCut() {

    }

    /**
     * 记录Service层的异常信息
     * @param joinPoint 代理对象
     * @param throwable 异常对象
     */
    @AfterThrowing(value = "servicePointCut()", throwing = "throwable")
    public void logServiceException(JoinPoint joinPoint, Throwable throwable) {
        String clazzName = joinPoint.getSignature().getDeclaringType().getSimpleName();
        Object[] args = joinPoint.getArgs();
        String method = joinPoint.getSignature().getName();
        log.error("[服务层出现异常]，类名：" + clazzName);
        log.error("异常方法名：" + method);
        log.error("异常参数列表：" + Arrays.toString(args));
        log.error("异常详情：" + throwable.toString());
        StackTraceElement[] elements = throwable.getStackTrace();
        StackTraceElement element = elements[0];
        log.error("[服务层异常位置]，异常方法名：" + element.getMethodName() + " 异常位置：" + element.getLineNumber());
    }
}
