# note


## 정렬기능 구현

stream 의 sorted 메서드와 Comparator 클래스로 구현한다.
```java
private List<T> list = new ArrayList<>();

public void 간단한_정렬(){
    list.stream()
            .sorted((o1, o2) -> o1 >= o2)
            .collect(Collectors.toList());
}

// 익명 클래스 또는 람다로 빼기 (셋다 동일함)
private Comparator<T> sort = new Comparator<T>() {
    @Override
    public int compare(T o1, T o2) {
        return Long.compare(o1.getId(), o2.getId());
    }
};
// 람다
Comparator<T> sortRamda = (o1, o2) -> Long.compare(o1.getId(), o2.getId());
// 메서드 참조
Comparator<T> sortMethodRef = Comparator.comparingLong(Entity::getId);

public void 정렬이_복잡해질_경우() {
    dataList.stream()
            .sorted(sort) // return Stream<T>
            .collect(Collectors.toList()); // return List<T>
}
```

## Optional 벗겨내기

```java
import java.util.ArrayList;

private Optional<List<T>> dataList = new ArrayList<>();

var prevData = dataList.stream()
        .filter(it -> it.getId().equals(data.getId()))
        .findFirst();

public void failture() {
    if(prevData.isPresent()) {
      // 왠지 dataList 는 List<T> 일 것 같은데 Optional<List<T>> 임
    } 
}

public void ok() {
    prevData.ifPresent((it) -> { // it 은 T
        });
    // 이런식으로 줄여 쓸때 좋은듯..
    prevData.ifPresent(dataList::remove); 
}
```

그외 is(if)Empty 도 있고 로직이 길어지지만 `dataList.get(i)`을 써도 된다.

~~대충 만들언 intellij 가 알아서 추천해줌~~


## @RequiredArgsConstructor

`@NonNull` 이나 `final` 로 선언된 필드의 생성자를 만들어 줌

요거를
```java
public class Controller() {
    private final UserService userService;

    public Controller(UserService userService) {
        this.userService = userService;
    }
}
```

이렇게 만들어 줌
```java
@RequiredArgsConstructor
public class Controller() {
    private final UserService userService;
}
```

## 스프링 컨테이너와 스프링 빈

스프링에서는 자바 객체를 관리 해주는 컨테이너를 스프링 컨테이너라고 하고,

컨테이너에서 관리하는 객체를 스프링 빈이라고 한다.

데이터에 접근하는 `@Repository`, 서비스 로직을 처리하는 `@Service`, 웹 요청을 처리하는 `@Controller`가 스프링 컨테이너의 관리 대상이고

그 외 스프링 빈에 추가하고 싶다면 `@Component`를 사용하면 된다.

[스프링 컨테이너와 스프링 빈](https://velog.io/@tank3a/%EC%8A%A4%ED%94%84%EB%A7%81-%EC%BB%A8%ED%85%8C%EC%9D%B4%EB%84%88%EC%99%80-%EC%8A%A4%ED%94%84%EB%A7%81-%EB%B9%88)

## @service

서비스 레이어는 @Service 어노테이션을 적어줘야한다. 

```java
@Service
public class UserService {
    
}
```

Service 계층이 아님에도 스프링 컨테이너에 등록해야 하는 경우는 `@Component`를 붙여주면 되고

외부 라이브러리라면 `@Configuration` -> `@Bean` 어노테이션을 붙이고 인스턴스를 리턴하면 스프링 컨테이너에 등록된다.

```java
@Configuration
public class DataBaseConfig {

    @Bean
    public UserRepository userRepository() {
        return new UserRepository();
    }
}
```