# note

## validation exception 처리하기

response 는 이런 형태를 기본적으로 가지도록 만든다.
```json
{
  "data": {},
  "status" : "HttpStatus.<CODE>.value()",
  "message" : "HttpStatus.<CODE>.getReasonPhrase()",
  "error" : ["요기에 validation 에러 넣기"]
}
```

ResponseEntity 클래스에 에러시 body 에 들어갈 위 JSON 객체를 만드는 클래스 `Api`를 정의한다.

```java
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Api<T> {

    private String status;

    private String message;

    @Valid
    private T data;

    private Error error;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
    public static class Error {
        private List<String> errorMessage;
    }
}
```

ValidationExceptionHandler 를 만든다.

```java
@Slf4j
@RestControllerAdvice
@Order(value = 1)
public class ValidationExceptionHandler  {

    @ExceptionHandler (value = {MethodArgumentNotValidException.class})
    public ResponseEntity<Api<? extends Object>> validationException(
            MethodArgumentNotValidException exception
    ) {
        var errorMessageList = exception.getFieldErrors().stream()
                .map(it -> {
                    var format = "%s : { %s } 은 %s";
                    return String.format(format, it.getField(), it.getRejectedValue(), it.getDefaultMessage());
                }).toList();

        var error = Api.Error
                .builder()
                .errorMessage(errorMessageList)
                .build();

        var errorResponse = Api.builder()
                .status(String.valueOf(HttpStatus.BAD_REQUEST.value()))
                .message(String.valueOf(HttpStatus.BAD_REQUEST.getReasonPhrase()))
                .error(error)
                .build();

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST.value())
                .body(errorResponse);
    }
}
```
