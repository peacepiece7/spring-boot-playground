package com.example.jwt_security.serivce;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.AeadAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

@Slf4j
@Service
public class JwtService {

    private static String secretKey = "sKKkakD132kEEkaSSS132akD132akD132akD132akDDk212132aS";

    public String create(
            Map<String, Object> claims,
            LocalDateTime expireAt
    ) {
        var key = Keys.hmacShaKeyFor(secretKey.getBytes());
        var _expireAt = Date.from(expireAt.atZone(ZoneId.systemDefault()).toInstant());

        AeadAlgorithm enc = Jwts.ENC.A256GCM;
//        return Jwts.builder()
//                .encryptWith(key, enc)
//                .claims(claims)
//                .expiration(_expireAt)
//                .compact();
        // 변경된 부분: encryptWith 대신 signWith 사용
        return Jwts.builder()
                .claims(claims)
                .expiration(_expireAt)
                .signWith(key)  // 서명 방식 지정
                .compact();
    };

    public void validation(String token) {
        var key = Keys.hmacShaKeyFor(secretKey.getBytes());

        var parser = Jwts.parser().setSigningKey(key).build();

        var result = parser.parseClaimsJws(token);

        result.getBody().entrySet().forEach(val -> log.info("key: {}, value: {}", val.getKey(), val.getValue()));
    }

    /**
     * @link https://github.com/jwtk/jjwt?tab=readme-ov-file#creating-a-jwt
     */
    public void create() {

        var tempExpiredAt = new Date();

        Map<String,String> claims = new HashMap<>();

        claims.put("username", "john_doe");
        claims.put("email", "john.doe@example.com");
        claims.put("role", "admin");

        String jwt = Jwts.builder()
                .header()
                .keyId("foo")
                .and()
                .subject("SUBJECT: JOE")
                .id(UUID.randomUUID().toString())
                .content("foo; bar barz") // payload
                .expiration(tempExpiredAt)
                .claims(claims) // map 으로 추가할 수있음
                .compact();
    }


    public void read(
            String payload
    ) {
        var key = Keys.hmacShaKeyFor(secretKey.getBytes());

        var jwtparser = Jwts.parser()
                .verifyWith(key) // tatic key used to verify all encountered JWSs
                // .keyLocator dynamically lookup verification keys based on each JWS
                .build();

        jwtparser.parseSignedClaims(payload);
    }
}
