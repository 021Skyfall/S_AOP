package Skyfall.AOP.Order.aop;

import org.aspectj.lang.annotation.Pointcut;

public class Pointcuts {
    @Pointcut("execution(* Skyfall.AOP.Order..*(..))")
    public void allOrder(){}
    @Pointcut("execution(* *..*Service.*(..))")
    public void allService(){}
    @Pointcut("allOrder() && allService()")
    public void orderAndService(){}
}