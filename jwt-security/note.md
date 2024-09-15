# note

이쪽에서 체크

https://jwt.io/libraries?language=Java

https://github.com/jwtk/jjwt?tab=readme-ov-file#gradle

## JWT

클라이언트에서 저장하도록 만든 토큰

header, payload, verify signature 세 단계로 나눠져 있고 "." 로 구분 함

JWT 도 JWS, JWE 방식이 있는데 여기선 비교적 간단한 JWS 를 사용함

## JWT 생성

```java
private static String secretKey = "시크릿 키";

public String createJwtToken(
        Map<String, Object> claims,
        LocalDateTime expireAt
) {
    var key = Keys.hmacShaKeyFor(secretKey.getBytes());
    var _expireAt = Date.from(expireAt.atZone(ZoneId.systemDefault()).toInstant());
    AeadAlgorithm enc = Jwts.ENC.A256GCM;
    return Jwts.builder()
            // .header().keyId().and() 이런식으로 헤더 추가
            .encryptWith(key, enc)
            .claims(claims) // payload 들어갈 내용
            .expiration(_expireAt) // 만료일
            .compact();
}
``` 

### JWT 읽기

```java
public void read(
        String payload
) {
    var key = Keys.hmacShaKeyFor(secretKey.getBytes());
    var jwtParser = Jwts.parser()
            .verifyWith(key) // tatic key used to verify all encountered JWSs
            // .keyLocator dynamically lookup verification keys based on each JWS
            .build();
    jwtParser.parseSignedClaims(payload);
}
```
