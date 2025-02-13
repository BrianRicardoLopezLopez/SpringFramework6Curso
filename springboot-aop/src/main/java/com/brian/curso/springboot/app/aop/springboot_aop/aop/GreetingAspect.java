package com.brian.curso.springboot.app.aop.springboot_aop.aop;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class GreetingAspect {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    // cuidado como pone los puntos al final porque so afecta mucho en la salida a
    // lo veo esto es lo que llamas si pones dos variables eso son dos puntos si
    // pones mas cambia dependiendo
    // @Before("execution(*
    // com.brian.curso.springboot.app.aop.springboot_aop.services.GreetingService.*(..))")
    // public void loggerBefore(JoinPoint joinPoint){

    @Before("execution(* com.brian.curso.springboot.app.aop.springboot_aop.services.GreetingService.*(..))")
    public void loggerBefore(JoinPoint joinPoint) {

        String method = joinPoint.getSignature().getName();
        String args = Arrays.toString(joinPoint.getArgs());
        logger.info("Antes: " + method + " con los argumentos " + args);
    }

    @After("execution(* com.brian.curso.springboot.app.aop.springboot_aop.services.GreetingService.*(..))")
    public void loggerAfter(JoinPoint joinPoint) {

        String method = joinPoint.getSignature().getName();
        String args = Arrays.toString(joinPoint.getArgs());
        logger.info("Despues: " + method + " con los argumentos " + args);
    }

    @AfterReturning("execution(* com.brian.curso.springboot.app.aop.springboot_aop.services.GreetingService.*(..))")
    public void loggerAfterReturning(JoinPoint joinPoint) {

        String method = joinPoint.getSignature().getName();
        String args = Arrays.toString(joinPoint.getArgs());
        logger.info("Despues de retornar: " + method + " con los argumentos " + args);
    }

    @AfterThrowing("execution(* com.brian.curso.springboot.app.aop.springboot_aop.services.GreetingService.*(..))")
    public void loggerAfterThrowing(JoinPoint joinPoint) {

        String method = joinPoint.getSignature().getName();
        String args = Arrays.toString(joinPoint.getArgs());
        logger.info("Despues de lanzar la exception: " + method + " con los argumentos " + args);
    }

    @Around("execution(* com.brian.curso.springboot.app.aop.springboot_aop.services.GreetingService.*(..))")
    public Object loggerAround(ProceedingJoinPoint joinPoint) throws Throwable {
        String method = joinPoint.getSignature().getName();
        String args = Arrays.toString(joinPoint.getArgs());

        Object result = null;
        try {
            logger.info("el metodo " + method + " () con los parametros "+ args);
            result = joinPoint.proceed(); 
            logger.info("el metodo " + method + " () retorna el resultado: " + result);
            return result;
            
        } catch (Throwable e) {
            logger.info("Error en la llamada del metodo " + method + " ()" );
            throw e;

        }

    }
}
