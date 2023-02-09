package Skyfall.AOP;

import Skyfall.AOP.Order.OrderRepository;
import Skyfall.AOP.Order.OrderService;
import Skyfall.AOP.Order.aop.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@Slf4j
@SpringBootTest
//@Import(Aspect1.class) // AOP 적용
//@Import(Aspect2.class) // 포인트컷 분리 테스트
//@Import(Aspect3.class) // 어드바이스 추가
//@Import(Aspect4.class) // 포인트컷 참조
//@Import({Aspect5.LogAspect.class,Aspect5.TxAspect.class}) // 어드바이스 순서
@Import(Aspect6.class) // 어드바이스 종류
public class AopTest {
    OrderService orderService;
    OrderRepository orderRepository;

    @Autowired
    public AopTest(OrderService orderService, OrderRepository orderRepository) {
        this.orderService = orderService;
        this.orderRepository = orderRepository;
    }

    @Test
    void aopInfo() {
        log.info("isAopProxy, orderService={}", AopUtils.isAopProxy(orderService));
        log.info("isAopProxy, orderRepository={}", AopUtils.isAopProxy(orderRepository));
    }
    @Test
    void success() {
        orderService.orderItem("itemA");
    }
    @Test
    void exception() {
        assertThatThrownBy(() -> orderService.orderItem("ex"))
                .isInstanceOf(IllegalStateException.class);
    }
}

