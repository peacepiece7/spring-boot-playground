package com.example.aop.aop;

import com.example.aop.model.UserRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.util.Arrays;
import java.util.Objects;

@Aspect
@Component
public class TimerAop {
    // String Bean 에만 AOP 가능 String Bean 이 아니라면 aspectj 키워드 검색
    @Pointcut(value = "within(com.example.aop.controller.UserApiController)")
    public void timerPointCut( ) {}

    @Before(value = "timerPointCut()")
    public void before(JoinPoint joinPoint) {}

    @After(value = "timerPointCut()")
    public void after(JoinPoint joinPoint) {}

    @AfterReturning(value = "timerPointCut()", returning = "result")
    public void afterReturning(JoinPoint joinPoint, Object result) {}

    @AfterThrowing(value = "timerPointCut()", throwing = "throwing")
    public void afterThrowing(JoinPoint joinPoint, Throwable throwing) {}

    @Around(value = "timerPointCut()") // timerPointCut 메서드의 @Pointcut 설정 내용으로 @Around 하겠다
    public void around(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("메소드 실행 이전");

        Arrays.stream(joinPoint.getArgs())
                        .forEach(it -> {
                            if(it instanceof UserRequest tempUserRequest) {
                                tempUserRequest.setPhoneNumber(tempUserRequest.getPhoneNumber().replaceAll("-", ""));
                            }
                        });

        var stopWatch = new StopWatch();
        stopWatch.start();

        // 암/복호화, masking, 로깅, 일괄 처리 시
        // Spring transaction 이 이렇게 되어있음, 서비스가 느릴경우 (apm 이 없을 경우)
        var newObjs = Arrays.asList(new UserRequest());
        joinPoint.proceed(newObjs.toArray());

        // joinPoint.proceed();

        stopWatch.stop();

        System.out.println("총 소요 시간: "+stopWatch.getTotalTimeMillis()+"ms");

        System.out.println("메소드 실행 이후");
    }
}
