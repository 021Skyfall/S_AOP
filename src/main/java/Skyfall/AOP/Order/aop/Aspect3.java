package Skyfall.AOP.Order.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Slf4j
@Aspect
public class Aspect3 {
    @Pointcut("execution(* Skyfall.AOP.Order..*(..))")
    private void allOrder(){}

    // 타입 이름 패턴이 Service 로 끝나는 것을 대상으로 지정
    // 패턴이라고 하는 이유는 클래스, 인터페이스에 모두 적용되기 때문
    @Pointcut("execution(* *..*Service.*(..))")
    private void allService(){}

    // 즉, OrderRepository 에는 현재 AOP 만 적용
    @Around("allOrder()")
    public Object doLog(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("log -> {}", joinPoint.getSignature());
        return joinPoint.proceed();
    }

    // OrderService 에 만 적용
    @Around("allOrder() && allService()")
    public Object doTransaction(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            log.info("트랙잭션 시작 -> {}", joinPoint.getSignature());
            Object result = joinPoint.proceed();
            log.info("트랜잭션 커밋 -> {}", joinPoint.getSignature());
            return result;
        } catch (Exception e) {
            log.info("트랜잭션 롤백 -> {}", joinPoint.getSignature());
            throw e;
        } finally {
            log.info("리소스 릴리즈 -> {}", joinPoint.getSignature());
        }
    }
}
