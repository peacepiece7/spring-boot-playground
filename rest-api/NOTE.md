# NOTE

## jvm 버전이 번경이 안됨

spring initializer 로 spring 3.2, java 17로 했는데 java 11로 빌드 되어 gradlew 에서 에러가 나옴


자바 버전 체크
```bash
java --version
```

gradlew로 jvm 버전 뭐 쓰는지 체크
```bash
gradlew -version
```

1. project structure 에서 jdk 17을 다운로드 받는다.
2. ctrl + p -> 전체 탭 이동 -> build tools -> gradlew 에서 java 17로 변경

또는 

1. jdk se 17을 웹에서 설치
2. java --version 으로 17 설치 확인
3. gradlew.properties 에 org.gradle.java.home=/path/to/your/java17 추가

JAVA_HOME 환경 변수도 체크해보자

```bash
export JAVA_HOME=/path/to/your/java17
```

근데 jdk를 찾을 수 없다고 나와서, 얼럿에서 자바 버전 선택하는 창이 뜨는데 그걸로 버전 탐지하게 함..


# GET mapping

어노테이션으로 매핑하고 어쩌고 다 하는데, 이걸 안하면 xml 파일에 매핑을 어디다할지 일일이 적어줘야 한다고 함

@RestController // REST API 임을 알림
@RequestMapping("/api") // API 주소
@GetMapping(path = "/hello")  // API 매핑 주소
```java
// 이케하면 get /api/hello 임
@RestController
@RequestMapping("/api")
public class RestApiController {

    @GetMapping(path = "/hello")
    public String hello() {
        return "Hello Spring boot";
    }
}
```


# terminal 한글 꺠짐

UTF-8이 기본인데 ms는 ms949 포멧이라서 한글이 깨짐

UTF-8 3byte, ms949나 euc-kr은 2byte 로 한글을 표현해서 깨짐 

ctrl + p -> 전체 -> file encoding 검색

global encoding, project encoding utf-8로 변경

terminal 도 utf-8로 찾아서 바꿔주기!


# 8080 사용중일 경우

```cmd
netstat -ano | findstr 8080
taskkill /f /pid <pid>

lsof -i :8080
kill -9 <pid>
```

# post api 한글 꺠짐

help -> vm option 또는

ctrl + p, shift + tab x2 -> vm option 검색

-Dfile.encoding=UTF-8
-Dconsole.encoding=UTF-8

ctrl + sift + w 또는
ctrl + p -> shift + tab x2 -> restart ide 

# lombok snake_case 응답하기

```java
@Data // getter setter
@AllArgsConstructor // all 생성자
@NoArgsConstructor // 기본 생성자
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)  // 이렇게 추가하면 스네이크_케이스로 응답하게 됨
public class UserRequest {
    private String userName;
    private int userAge;
    private String email;
}
```

# lombok boolean 매칭 안되는 문제

structure(alt + 7)에 보면 setKorean, korean 으로 setter,getter 가 생성되어 매칭이 안되는 문제 발생

reference type Boolean 으로 변경해주면 됨
```java
@Data // getter setter
@AllArgsConstructor // all 생성자
@NoArgsConstructor // 기본 생성자
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class) // snake_case 변경
public class UserRequest {
    private String userName;
    private int userAge;
    private String email;
    private boolean isKorean; // default: false, not matched
    private Boolean isKorean; // matched
}
```

## Object Mapper 동작 원리

Jackson, Gson 같은걸로 DTO -> JSON 직렬화, JSON -> DTO 역직렬화를 함

jackson을 사용하면 아래와 같이 역/직렬화가 가능하다.

```java
@Autowired
private ObjectMapper objectMapper;

var json = objectMapper.writeValueAsString(user);
var dto = objectMapper.readValue(json, UserRequest.class);
```

이는 controller 에서도 동일하게 동작함

```java
@RestController
@RequestMapping("/api")
public class PostController {
    @PostMapper("/post")
    public String get(@RequestBody User user){ //  objectMapper.readValue(wjson, UserRequest.class); == JSON.parse(json)
        return user; // objectMapper.writeValueAsString(user); == JSON.stringify(user)
    }
}
```

즉 Spring에서 자동(암시적)으로 objectMapper를 사용하여 역/직렬화 작업을 해주는 것임

### 직렬화 과정

직렬화 할 때는 toString 메서드를 호출함

즉 toString 메서드를 오버라이드하면 직렬화했을 때 내용을 변경할 수 있음

js의 valueOf, toString 이랑 같음 "[Object object]" 이거 나오는 상황이라고 생각면 됨 
```java

public class User {
    // ... 
    @Override
    public String toString() {
        return "foo bar";
    }
}
var json = objectMapper.writeValueAsString(user);
var dto = objectMapper.readValue(json, UserRequest.class);
System.out.println("OBJECT MAPPER DTD: "+dto); ;// foo bar
```
### 역직렬화 과정

역직렬화는 getter로 가져온다.

```java
public class User {
    private String userName;
    private Integer userAge;
    private String email;
    
    // 선언된 멍멍이가 없어도 가져올 수 있다.
    public String getDog( ) { 
        return "bark!";
    }
}
var json = objectMapper.writeValueAsString(user); // { ...., dog : "bark!" }
```

### 가장 좋은 방법

특별한 요구사항이 없다면 lombok의 애너테이션으로 get/setter, constructor를 만들어두고 예외 사항은

@JsonIgnore, @JsonProperty 애너테이션을 사용하자

```java
@Data // getter, setter 추가
@AllArgsConstructor // 파라메터를 전부 받는 생성자 추가
@NoArgsConstructor // 파라메터를 하나도 안 받는 생성자 추가
public class User { 
    // ...
 @JsonProperty("user_email")
 private String email;
 
 @JsonIgnore
 public String getUserName() {
   return this.userName;
 }
}
```

### Exception

controller에서 발생한 예외를 처리하는데 사용됨 exception 파일안에 클래스를 만들어주면 된다.

#### @RestControllerAdvice

인자로 annotation, class, string(path) 등을 지정해줄 수 있다.

```java
@RestControllerAdvice(basePackages = "com.example.exceptionTest.controller")
public class RestApiExceptionHandler {}
```

#### @ExceptionHandler

예외 처리할 클래스를 바인딩 해준다.

```java
@ExceptionHandler(value = {NumberFormatException.class})
public ResponseEntity<Void> numberFormatedException(
        NumberFormatedException e
) {
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
}
```

#### @Builder

Builder 패턴으로 인스턴스를 생성할 수 있다.

> The builder annotation creates a so-called 'builder' aspect to the class that is annotated

```java
// model/Api.java
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Api <T> {
    private String resultCode;
    private String resultMessage;
    private T data;
}

// exception/GlobalExceptionHandler
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<Api<Object>> globalException(
            Exception e
    ) {
        var res = Api.builder()
                .resultCode(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()))
                .resultMessage(HttpStatus.INTERNAL_SERVER_ERROR.name())
                .build();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);
    }
}
```

#### @Order (Global Exception)

예외 처리의 우선순위를 결정한다.

> @Order defines the sort order for an annotated component.
> The value is optional and represents an order value as defined in the Ordered interface. **Lower values have higher priority**

기본값 Integer.MAX_VALUE 이고 값이 적을 수록 우선순위가 높다.

```java
// exception/RestApiExceptionHandler

// * 우선순위가 global Exception(Integer.MAX_VALUE) 보다 높기떄문에 먼저 실행된다. 여기서 못찾으면 globalException으로 넘어간다.
@Order (value = 1)
@Slf4j
@RestControllerAdvice(basePackages = "com.example.exceptionTest.controller")
public class RestApiExceptionHandler {
    @ExceptionHandler(value = {IndexOutOfBoundsException.class})
    public ResponseEntity<Void> HandleOutOfBoundException(
            IndexOutOfBoundsException e
    ) {
        log.error("@@ IndexOutOfBoundsException: ", e);
        return ResponseEntity.status(200).build();
    }
}

// exception/GlobalExceptionHandler
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = {Exception.class}) // Exception 최상위(?) 클래스
    public ResponseEntity<Api<Object>> globalException(
            Exception e
    ) {
        var res = Api.builder()
                .resultCode(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()))
                .resultMessage(HttpStatus.INTERNAL_SERVER_ERROR.name())
                .build();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);
    }
}
```



