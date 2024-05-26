package ru.kpfu.itis.skillshare.loggingstarter.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;


@Aspect
public class AnnotationLoggingAspect {

    public static final Logger LOGGER = LoggerFactory.getLogger(AnnotationLoggingAspect.class.getName());

    @Pointcut("@annotation(ru.kpfu.itis.skillshare.loggingstarter.annotation.Loggable)")
    public void logExecutionTime() {
    }

    @Around("logExecutionTime()")
    public Object log(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();

        String className = methodSignature.getDeclaringType().getName();
        String methodName = methodSignature.getName();

        final StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        Object result;

        result = joinPoint.proceed();

        stopWatch.stop();

        LOGGER.info("Execution time of {}.{} = {} ms", className, methodName, stopWatch.getTotalTimeMillis());

        return result;
    }


}