package com.springmvc.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
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

    @Before("execution(* com.springmvc.handle.SuccessHandler.*(..))")
    public void myAdvice(JoinPoint jp){
        System.out.println("Hey some one is trying to login");

    }
}
