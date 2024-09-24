# delivery service

바닥부터 만들어보기

## 멀티 모듈 설정

프로젝트 생성

alt + f -> alt + n -> alt + j
![img](./imgs/1.png)

새로운 모듈 추가 

service dir -> alt + insert -> Module... click

![img](./imgs/2.png)

root settings.gradle 보면 하위 종속성으로 모듈 추가된거 나옴

![img](./imgs/3.png)

그리고 똑같이 db 모듈도 만들어줌

db 모듈에 

이런 클래스 추가
```java
package org.delivery.db;

public class UserDto {
}
```

root build.gradle 파일에 다음 내용 추가

```txt
// service 하위 모든 저장소는 maven 을 쓰겠다
allprojects {
    repositories {
        mavenCentral()
    }
}
```

api 모듈에 db를 의존성으로 추가

아래사진처럼 compileClasspath project.db 추가 된 것을 확인한다.
![img](./imgs/4.png)

## @MappedSuperclass

직접 테이블에 매핑되지 않지만 해당 필드를 상속받아 쓸수 있음 (createdAt, UpdatedAt, Id 같은 것들에 사용됨)

```java
@MappedSuperclass
public abstract class BaseEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}

@Entity
public class User extends BaseEntity {
    // ...rest attrs
}
```

## Using @EqualsAndHashCode for JPA entities is not recommended. It can cause severe performance and memory consumption issues. 

`@EqualsAndHashCode(callSuper = true)` 어노테이션은 Equal, HashCode 오버라이드해준다.

callSuper = true 일 경우 부모 클래스의 속성도 포함된다.

`@Data`, `@EqualsAndHashCode` 어노테이션 모두 메모리 소비 이슈로 추천하지 않고, 다음 코드처럼 변경하길 권한다.

```java
@Getter
@Entity
@NoArgsConstructor(access = PROTECTED)
public class Users {

    /// ... 생략

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
            return false;
        }
        Users that = (Users) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}
```
[Warnings when using @EqualsAndHashCode for JPA entities](https://youtrack.jetbrains.com/issue/IDEA-279243/Warnings-when-using-EqualsAndHashCode-for-JPA-entities)


## @SuperBuilder

엔티티를 상속 받을 경우 부모 엔티티의 속성을 쓰고 싶다면 `@SuperBuilder`를 사용해야한다.

```java
@MappedSuperclass
@SuperBuilder
public class BaseEntity {}

@Entity
@Table(name = "account")
@SuperBuilder
public class AccountEntity extends BaseEntity {
    // ... rest attrs
}
```

## 멀티모듈 Bean 등록 문제

Spring Boot 는 `@SpringBootApplication` 이 있는 패키지와 그 하위 패키지를 기본으로 스캔하여 빈으로 등록한다.

`@SpringBootApplication`의 스캔 규칙으로 패키지 명이 동일하면 멀티 모듈에서도 자동으로 스캔되는데

그렇기에 org.delivery.db.~.java 파일과 org.delivery.api.~.java 파일은 동일한 컨테이너에 빈으로 등록 할 수 없다.

해결 방법으로 패키지명을 동일하게 바꿔주거나

다음과 같이 설정해줄 수 있다.
```java
@Configuration
@EntityScan(basePackages = "org.delivery.db")
@EnableJpaRepositories(basePackages = "org.delivery.db")
public class JpaConfig { 
    
}
```

## ObjectMapper 자주 사용하는 옵션 설명

```java
// object mapper 를 안만들면 스프링에서 default 로 하나 만들어 줌 만들었으니까 내것으로 적용될 것
@Configuration
public class ObjectMapperConfig {

    @Bean
    public ObjectMapper objectMapper() {
        // 존슨을 자바 객체로 변환하거나, 자바 객체를 존슨으로 변환하는 데 사용한다.
        var objectMapper = new ObjectMapper();

        // jdk 8 버전 이후에 나온 클래스, 기능을 Jackson 이 처라할 수 있도록 해준다.
        objectMapper.registerModule(new Jdk8Module());

        // Java 8의 java.time.LocalDate, java.time.LocalDateTime 등을 Jackson 이 처리할 수 있도록 해준다.
        objectMapper.registerModule(new JavaTimeModule());

        // JSON 데이터에 포함된 예상하지 못한 필드를 처리하는 방식을 지정한다.
        // 정의되지 않은 필드를 만나면 Exception 발생이 default, false 시 이를 무시하고 de/serialization 수행
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        // Getter 가 없는 빈 클래스를 직렬화 시 예외 발생이 default, false 시 이를 무시하고 de/serialization 수행, 모든 필드가 null 일 경우 사용됨
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false); //

        // Jackson 은 TimeStamp 로 직렬화 하는게 default, disable 시 ISO 8601 형식으로 직렬화 됨, LocalDate, LocalDateTime 을 가독성 좋게 변경할 떄 사용
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        // Java 객채 필드의 이름을 JSON 필드 이름으로 변환할 떄 Snake Case 스타일 지정
        // 요거 있으면 @JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class) 안붙여도 됨
        objectMapper.setPropertyNamingStrategy(new PropertyNamingStrategies.SnakeCaseStrategy());

        return objectMapper;
    }
}
```

## Swagger 설정하기

mavenRepository SpringDoc 검색

1.8v 까지 나왔는데 강의랑 맞추기 위해 1.7 씀

springDoc 패키지를 build.gradle 설정 파일에 추가 
```text
// Swagger(Spring 3.x.x 이상부터 SpringFox 대신, SpringDoc)
implementation 'org.springdoc:springdoc-openapi-starter-webmvc-ui:2.0.2'
```

### swagger config 설정

```java
@Configuration
public class SwaggerConfig {

    // ObjectMapperConfig.java에 정의한 objectMapper 메서드가 실행되면 리턴값이 요기 파라메터(objectMapper)로 주어짐
    @Bean
    public ModelResolver modelResolver (ObjectMapper objectMapper) {
        return new ModelResolver(objectMapper);
    }
}
```

