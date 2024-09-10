# aop

Aspect Oriented Programming

@Aspect: AOP 프레임워크 해당, AOP 정의하는 class에 할당

@Pointcut: AOP 적용 지점

@Before: 메소드 실행 전

@After: 메소드 실행 후 (Exception 되어도 실행)

@AfterReturning: 메소드 호출 성공 실행 시

@AfterThrowing: 메소드 호출 실패 예외 발생

@Around: Before, After 모두 제어



## @Pointcut designators

execution: 반환타입, 타입, 메소드, 파라미터 등 정밀한 표현

within: 특정 경로

this: 특정 타입의 객체

target: 특정 타입의 객체

args: 특정 타입의 파라미터를 가지는 메소드 기준

@target: 특정 어노테이션을 가지는 개체

@args: 특정 어노테이션의 파라메터를 가지는 메소드

@within: 특정 클래스의 경로의 어노테이션 기준

@annotation: 특정 메소드의 어노테이션 기준

bean: 스프링 빈을 기준으로 지정