package com.odev.yemektarifiodevi.aspect;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;


import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
@Component
public class LoggingAspect {

    Logger logger= LoggerFactory.getLogger(LoggingAspect.class);


    @Pointcut(value = "execution(* com.odev.yemektarifiodevi.controller.*.*(..))")
    public void myPointcut(){

    }


    @Around("myPointcut()")
    public Object pointcutLogger(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        ObjectMapper mapper=new ObjectMapper();
        String methodName=proceedingJoinPoint.getSignature().getName();
        String className=proceedingJoinPoint.getTarget().toString();
        Object[] array=proceedingJoinPoint.getArgs();

        if(methodName.equals("creteToken")){
            logger.info("method invoked "+className+" : "+methodName+"()"+" arguments : "+mapper.writeValueAsString(array));

            String  object= (String) proceedingJoinPoint.proceed();

            logger.info(className+" : "+methodName+"()"+" Response : "+object);

            return object;
        }else{
            logger.info("method invoked "+className+" : "+methodName+"()"+" arguments : "+mapper.writeValueAsString(array));

            ResponseEntity<Object> object= (ResponseEntity<Object>) proceedingJoinPoint.proceed();

            logger.info(className+" : "+methodName+"()"+" Response : "+mapper.writeValueAsString(object.getBody()));

            return object;
        }


    }


}
