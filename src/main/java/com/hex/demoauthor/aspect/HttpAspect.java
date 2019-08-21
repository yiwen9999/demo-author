package com.hex.demoauthor.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * http拦截
 * User: hexuan
 * Date: 2017/8/14
 * Time: 下午1:54
 */
@Aspect
@Component
@Slf4j
public class HttpAspect {

    @Pointcut("execution(public * com.hex.demoauthor.controller.*.*(..))")
    public void log() {
    }

    @Before("log()")
    public void doBegin(JoinPoint joinPoint) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        // url
        log.info("* url={}", request.getRequestURI());
        // method
        log.info("* method={}", request.getMethod());
        // ip
        log.info("* ip={}", request.getRemoteAddr());
        // 类方法
        log.info("* class_method={}", joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        log.info("");
    }

}
