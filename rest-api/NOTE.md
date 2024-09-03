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