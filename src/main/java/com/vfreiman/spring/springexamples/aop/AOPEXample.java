package com.vfreiman.spring.springexamples.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Aspect
@Component("aOPEXample")
@Qualifier("aOPEXample")
@Scope("singleton")
public class AOPEXample {

    @Around("execution(* com.vfreiman.spring.springexamples.beans.TestBean.print())")
    public Object testBeanPrint(ProceedingJoinPoint proceedingJoinPoint){
        System.out.println("Before invoking TestBean's print() method");
        Object value = null;
        try {
            value = proceedingJoinPoint.proceed();
        } catch (Throwable e) {
            e.printStackTrace();
        }
        System.out.println("After invoking TestBean's print() method. Return value="+value);
        return value;
    }

    @Around("execution(* com.vfreiman.spring.springexamples.beans.TestBean2.print())")
    public Object testBean2Print(ProceedingJoinPoint proceedingJoinPoint){
        System.out.println("Before invoking TestBean2's print() method");
        Object value = null;
        try {
            value = proceedingJoinPoint.proceed();
        } catch (Throwable e) {
            e.printStackTrace();
        }
        System.out.println("After invoking TestBean2's print() method. Return value="+value);
        return value;
    }
}
