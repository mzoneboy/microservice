package com.example.microservice.anno;

import com.example.microservice.intf.StuRpcService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
public class MonitorAspect {
    private static final Logger logger = LoggerFactory.getLogger(MonitorAspect.class);
    @Pointcut("execution(* com.example.microservice.intf.*.*(..))")
    private void pointCut(){

    }

    @Around("pointCut()")
    public Object monitorAround(ProceedingJoinPoint joinPoint) throws Throwable {
        Object obj = joinPoint.proceed();
        String className = joinPoint.getTarget().getClass().getName();
        logger.info("AOP=============class name:{}", className);
        Method[] declaredMethods = joinPoint.getTarget().getClass().getDeclaredMethods();
        for(Method method : declaredMethods){
            logger.info("AOP=============method name:{}", method.getName());
        }
        Object[] args = joinPoint.getArgs();
        for(int i=0;i<args.length;i++){
            logger.info("AOP================= arg:{}", args[i]);
        }

        StuRpcService stuRpcService = (StuRpcService)joinPoint.getTarget().getClass().newInstance();
        logger.info("AOP================= invoke method result:{}", stuRpcService.add((int)args[0], (int)args[1]));
        String s = "123";
        return obj;
    }
}
