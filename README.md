# WIKI

## TOC

### AOP

Aspect Oriented Programming\
관점 지향적 프로그래밍 기법 ~~자세한건 모른다~~

[AOP note](./aop/note.md)\
[Source code](./aop/src/main/java/com/example/aop/aop/TimerAop.java)

### Cookie

`httpServletRequest`를 사용하여 Cookie set, get 구현

[Source code](./cookie/src/main/java/com/example/cookie/controller/UserApiController.java)

### Session

`HttpSession`를 사용하여 구현

[Session note](./session/note.md)\
[Source code](./session/src/main/java/com/example/session/controller/UserApiController.java)

### JWT

[jwt note](./jwt-security/note.md)\
[Source code](./service-api/api/src/main/java/org/delivery/api)

### Docker-compose

docker-compose.yml 파일 작성\
intellij docker extension 사용 cli 없이 실행\
docker desktop 사용

[Source code](./docker-compose/mysql/docker-compose.yml)

### Web Configuration

간단하게 global middleware 라고 볼 수 있다.\
Interceptor, CORS, resource handler 역할을 수행한다.

[note](./service-api/README.md)\
[Authorization interceptor](./service-api/api/src/main/java/org/delivery/api/config/web/WebConfig.java)


### Middleware(Interceptor, Filter, Resolver)

`HandlerMethodArgumentResolver`, `HandlerInterceptor`, `jakarta.servlet.Filter`\
라는 이름으로 문서를 작생했다.\
각각 기능마다 사용되는 위치와 제공하는 기능이 조금씩 다르다.

[note](./service-api/README.md)\
[Authorization interceptor](./service-api/api/src/main/java/org/delivery/api/interceptor/AuthorizationInterceptor.java)\
[User session resolver](./service-api/api/src/main/java/org/delivery/api/resolver/UserSessionResolver.java)\
[Logger filter](./service-api/api/src/main/java/org/delivery/api/filter/LoggerFilter.java)

`Interceptor`, `Resolver`는 작성 시 WebMvcConfigure 구현체에 DI 해준다.\
[Web Configuration](./service-api/api/src/main/java/org/delivery/api/config/web/WebConfig.java)

### Multi-module setting

api, db 모듈을 분리\
gradle.build 에서 의존성 주입\
네이밍 컨벤션이 있기 때문에 **멀티모듈 Bean 등록에 문제**가 생길 수 있다.\
자세한 건 아래 링크 참고

[multi-module note](./service-api/README.md)
[JpaConfig](./service-api/api/src/main/java/org/delivery/api/config/jpa/JpaConfig.java)

### Annotation customizing

annotation 생성\
경우에 따라서 추가로 validator 생성할 수 있다.

case 1) annotation & validator

[Password annotation](./yearly-idol-back/src/main/java/com/yearly/idol/api/yearly_idol/Common/annotation/Password.java)\
[Password annotation validator](./yearly-idol-back/src/main/java/com/yearly/idol/api/yearly_idol/Common/validator/PasswordValidator.java)

[Korean Only](./validation/src/main/java/com/example/validation/annotation/KoreanOnly.java)\
[Korean Only](./validation/src/main/java/com/example/validation/validator/KoreanOnlyValidator.java)

[Phone Number](./validation/src/main/java/com/example/validation/annotation/PhoneNumber.java)\
[Phone Number](./validation/src/main/java/com/example/validation/validator/PhoneNumberValidator.java)

case 2) spring bean annotation

[Business annotation](./service-api/api/src/main/java/org/delivery/api/common/annotation/Business.java)\
[Convertor annotation](./service-api/api/src/main/java/org/delivery/api/common/annotation/Converter.java)

case 3) session annotation

이게 쪼금 복잡하다.\
세션 정보를 가져오는 어노테이션을 사용하면 다음 흐름으로 세션 정보를 가져온다.\
interceptor -> resolver -> controller

자세히 보면 다음과 같다.

- annotation
  - 어노테이션 선언
  - [user session data annotation](./service-api/api/src/main/java/org/delivery/api/common/annotation/UserSession.java)
- interceptor
  - 다음 순서로 하나의 요청 스레드에 userId 추가
    - `var requestContext = RequestContextHolder.getRequestAttributes()`
    - `requestContext.setAttribute(userId)`
- resolver
  - 다음 순서로 `@UserSession`어노테이션을 사용할 경우 `Controller`에 사용자 정보 주입
      - `var requestContext = RequestContextHolder.getRequestAttributes()`
      - `var userId = requestContext.getAttribute(userId)`
      - `return userService.findById(userId)`
      - [User session resolver](./service-api/api/src/main/java/org/delivery/api/resolver/UserSessionResolver.java)
- controller
  - `@UserSession` 어노테이션을 붙으면 끗
  - [user session controller](./service-api/api/src/main/java/org/delivery/api/domain/user/controller/UserApiController.java)

### ObjectMapper setting

스프링 빈에 기본적으로 붙는 어노테이션 설정

기본 값으로 스프링에서 생성해준다.\
`@JsonNaimg(PropertyNamingStrategies.SnakeCaseStrategy.class)`\
같은 어노테이션을 반복해서 붙이고 있다면 이 곳에 추가한다.

[Object mapper](./service-api/api/src/main/java/org/delivery/api/config/objectMapper/ObjectMapperConfig.java)\
[Object mapper note](./service-api/README.md)

### 예외 처리 패턴

세가지 케이스를 봤는데 1, 2 번 케이스를 같이 사용하면 좋을 것 같다.

case 1)\
enum `ErrorStatus`와 GlobalExceptionHandler, ApiExceptionHandler 제공\
[reference](./service-api)

case 2)\
`@Validation` 에러시 Exception 에러를 자세히 알려주는 프론트 친화적 패턴\
[reference1](./yearly-idol-back)\
[reference2](./rest-and-validation-exercise/note.md)

case 3)\
case 1 + 도메인 별로 exception 설정

case 4)\
`ResponseEntity`를 컨트롤러에서 바로 내리고 ExceptionHandler 에서 모두 처리

### JPA SQL 직접 작성하기

repository layer에 작성한다.\
business layer가 없거나 쿼리 성능의 문제가 있다면 사용할 수 있다.

[Source code](./simple-performance/src/main/java/com/example/simple_performance/performance/db/PerformanceRepository.java)