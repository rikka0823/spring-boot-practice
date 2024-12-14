package com.rikka.springBootPractice.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyAspect {

    @Before("execution(* com.rikka.spring_boot_practice.component.HpComponent.*(..))")
    public void print() {
        System.out.println("before print");
    }

//    @After("execution(* com.rikka.spring_boot_practice.component.HpComponent.*(..))")
//    public void print1() {
//        System.out.println("after print");
//    }
//
//    @Around("execution(* com.rikka.spring_boot_practice.component.HpComponent.*(..))")
//    public Object around(ProceedingJoinPoint pjp) throws Throwable {
//        System.out.println("before print");
//        Object obj = pjp.proceed();
//        System.out.println("after print");
//        return obj;
//    }
    
}
