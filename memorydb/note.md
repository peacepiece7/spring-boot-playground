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