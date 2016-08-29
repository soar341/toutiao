package com.nowcoderExample.service.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2016/7/22 0022.
 */
@Aspect
@Component //否则,切面调用不被初始化
public class LogAspect {
    private static final Logger logger= LoggerFactory.getLogger(LogAspect.class);
    @Before("execution(* com.nowcoderExample.controller.IndexController.*(..))")
    public void beforeMethod(JoinPoint joinPoint){
        StringBuilder sb=new StringBuilder();
        for(Object args:joinPoint.getArgs()){
            sb.append("args:"+ args.toString()+"|");
        }
        logger.info("before method: "+sb.toString());
    }

    @After("execution(* com.nowcoderExample.controller.IndexController.*(..))")
    public void afterMethod(JoinPoint joinPoint){
        logger.info("after method: ");
    }
}
