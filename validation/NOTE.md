# NOTE


## Validation

jakarta bean validation 3.0 쓰면 됨 

[jakarta bean validation specification](https://jakarta.ee/specifications/bean-validation/3.0/jakarta-bean-validation-spec-3.0.html#builtinconstraints)

`8. Built-in Constraint definitions`이쪽에 있는 어노테이션을 먼저 공부하면 됨

GPT가 정리해준건데 validation 기억안나면 참고하자

```text
@Null: 값이 반드시 null이어야 함.

@NotNull: null 값이 허용되지 않음.

@AssertTrue: boolean 값이 true여야 함. (별도 로직 적용)

@AssertFalse: boolean 값이 false여야 함. (별도 로직 적용)

@Min: 지정된 최소값 이상이어야 함 (정수형).

@Max: 지정된 최대값 이하이어야 함 (정수형).

@DecimalMin: 지정된 최소값 이상이어야 함 (실수형).

@DecimalMax: 지정된 최대값 이하이어야 함 (실수형).

@Negative: 음수 값이어야 함.

@NegativeOrZero: 음수 또는 0이어야 함.

@Positive: 양수 값이어야 함.

@PositiveOrZero: 양수 또는 0이어야 함.

@Size:  "문자열"의 길이 (int 불가)

@Digits: 숫자 자릿수를 제한함. 정수와 소수 자릿수를 명시적으로 설정 가능.

@Past: 과거 날짜여야 함.

@PastOrPresent: 과거 또는 현재 날짜여야 함.

@Future: 미래 날짜여야 함.

@FutureOrPresent: 현재 또는 미래 날짜여야 함.

@Pattern: 지정된 정규식과 일치하는 문자열만 허용됨.

@NotEmpty: null, "" 불가

@NotBlank:  null, "", " "(\\s) 불가

@Email: 유효한 이메일 주소 형식이어야 함.

@Valid: 해당 객체의 유효성 검사를 트리거함. 중첩된 객체의 유효성 검사를 수행함.
```


iso 8610