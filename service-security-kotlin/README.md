#    

##    

## @NotNull, @Colum(nullable = false)

`NotNull`은 애플리케이션단에서 데이터가 null 값이 바인딩 될 경우 에러 발생

`@Colum(nullable = false)`는 데이터베이스단에서 검사를 수행, db 필드가 `null`을 허용해도 해당 어노테이션이 붙어있으면\
데이터를 추가/수정 할 때 에러 발생
