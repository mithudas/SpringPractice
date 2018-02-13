package com.springmvc.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

/**
 * Created by mithu on 13/2/18.
 */

@Aspect
@Component
public class TrackOperation {
   /* @Pointcut("execution(* com.springmvc.service.SpringSecurityService.*(..))")
    public void myPointcut(){

    }*/

    @Before("execution(* com.springmvc.handle.SuccessHandler.onAuthenticationSuccess(..))")
    public void myBeforeAdvice(JoinPoint jp){
        System.out.println("Hey some one is trying to login");

    }

    @After("execution(* com.springmvc.handle.LogoutSuccessHandler.onLogoutSuccess(..))")
    public void myAfterAdvice(JoinPoint jp){
        System.out.println("logout done>>>>>>");

    }

    @Around("execution(* com.springmvc.controller.AopTestController.aopTest(..))")
    public void aroundAdvice(JoinPoint jp){
        System.out.println("its around advice>>>>>");
    }

    @AfterThrowing("execution(* com.springmvc.controller.AopTestController.aopWithException(..))")
    public void throwingAdvice(JoinPoint jp) {
        System.out.println("hey this exception is thrown");
    }


    @AfterReturning(
            pointcut = "execution(* com.springmvc.controller.AopTestController.aopTest(..))")
    public void afterReturningAdvice(JoinPoint jp){
        System.out.println("hey this will be there if it returns anything");
        System.out.println(jp.getArgs());
        System.out.println(jp.getKind());
        System.out.println(jp.getSignature());
        System.out.println(jp.getSourceLocation());
        System.out.println(jp.getSourceLocation());
        System.out.println(jp.getTarget());
        System.out.println(jp.getThis());
        System.out.println(jp.toLongString());
        System.out.println(jp.toShortString());
        System.out.println(jp.toString());
    }



}
