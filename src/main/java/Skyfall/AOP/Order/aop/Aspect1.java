package Skyfall.AOP.Order.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Slf4j
@Aspect
public class Aspect1 {
    //@어드바이스(포인트컷)
    //즉, 현재 어드바이스인 메서드는 loggin
    //execution(~) -> Skyfall.AOP.order 패키지와 하위 패키지를 지정한 AspectJ 포인트컷 표현식
    //즉, OrderService 와 OrderRepository 의 모든 메서드는 AOP 적용 대상임
    @Around("execution(* Skyfall.AOP.Order..*(..))")
    public Object logging(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("log -> {}", joinPoint.getSignature());
        return joinPoint.proceed();
    }
}
