# @ManyToOne, @OneToMany

조인이 필요한 경우 `@Transient`로 엔티티에 예외를 걸어줄 수 도 있지만 

OOP 적으로 해결하면 `@ManyToOne`, `@OneToMany`를 사용하는게 좋음

`@ManyToOne`: mappedBy로 어떤 속성과 연관되었는지 짝지어 줌

`@OneToMany`: `@ManyToOne`와 연결되는 속성에 어노테이션을 붙여주면 됨

만약 postEntity 에 붙여주면 DB의 post_entity_id 를 찾아서 조인함

조인하는 속성명이 다른 경우 `@JoinColumn(name = "board_id")`이렇게 처리

사용 방법은 다음과 같음 
```java
@Entity(name = "board")
public class BoardEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToMany(mappedBy = "board")
    @ToString.Exclude
    private List<PostEntity> postList = List.of();
    
    //...rest
}

public class PostEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne()
    private BoardEntity board;
    
    //...rest
}
```

## 지연 로딩 문제 (Could not write JSON: failed to lazily initialize a collection of role)

spring boot 2 버전에서는 위와 같이 작성하면 순환 참조되는 문제가 있엇는데,

3버전으로 하니까 lazy loading 관련 문제가 발생했다.

`@OneToMany( fetch = FetchType.LAZY)` 가 default 로 설정되어 있다. 

일단은 `@OneToMany(fetch = FetchType.EAGER)`로 했는데

영속성과 관련된 문제인 듯 하고 JPA 를 처음 사용하면 한번 쯤 겪어야 하는 문제인가 보다.

고민 후 다시 기록하도록 하자.

[지연 로딩과 N+1 문제](https://sjh9708.tistory.com/160)

[참고 1](https://www.inflearn.com/community/questions/285010/could-not-write-json-failed-to-lazily-initialize-a-collection-of-role)


### LazyInitializationException 문제

[다음 블로그](https://wjcodding.tistory.com/81)를 참고하여 일단 controller 에 `@Transactional` 추가