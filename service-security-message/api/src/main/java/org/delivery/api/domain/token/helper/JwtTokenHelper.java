package org.delivery.api.domain.token.helper;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.delivery.api.common.error.TokenErrorCode;
import org.delivery.api.common.exception.ApiException;
import org.delivery.api.domain.token.ifs.TokenHelperIfs;
import org.delivery.api.domain.token.model.TokenDto;
import org.springframework.beans.factory.annotation.Value; // lombok value 아님..!
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.sql.Date;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class JwtTokenHelper implements TokenHelperIfs {

    @Value("${token.secret.key}")
    private String secretKey;

    @Value("${token.access-token.plus-hour}")
    private Long accessTokenPlusHour;

    @Value("${token.refresh-token.plus-hour}")
    private Long refreshTokenPlusHour;

    @Override
    public TokenDto issueAccessToken(Map<String, Object> data) {
        var expiredLocalDateTime = LocalDateTime.now().plusHours(accessTokenPlusHour);

        var expiredAt = Date.from(
                expiredLocalDateTime.atZone(
                        ZoneId.systemDefault()
                ).toInstant()
        );

        var key = Keys.hmacShaKeyFor(secretKey.getBytes());

        var jwtToken = Jwts.builder()
                .signWith(key)
                .claims(data)
                .expiration(expiredAt)
                .compact();

        return TokenDto.builder()
                .token(jwtToken)
                .expiredAt(expiredLocalDateTime)
                .build();
    }

    @Override
    public TokenDto issueRefreshToken(Map<String, Object> data) {
        var expiredLocalDateTime = LocalDateTime.now().plusHours(refreshTokenPlusHour);

        var expiredAt = Date.from(
                expiredLocalDateTime.atZone(
                        ZoneId.systemDefault()
                ).toInstant()
        );

        var key = Keys.hmacShaKeyFor(secretKey.getBytes());

        // @NOTE jwt 사용자 정보 안 넣음
        Map<String, String> claims = new HashMap<>();

        var jwtToken = Jwts.builder()
                .claims(claims)
                .signWith(key)
                .expiration(expiredAt)
                .compact();
                // deprecated
                // .signWith(key, SignatureAlgorithm.HS256)

        return TokenDto.builder()
                .token(jwtToken)
                .expiredAt(expiredLocalDateTime)
                .build();
    }

    @Override
    public Map<String, Object> validationTokenWithThrow(String token) {
        var key = Keys.hmacShaKeyFor(secretKey.getBytes());

        var parser = Jwts.parser()
                .verifyWith(key)
                .build();
                // deprecated .setSigningWithKey(key)

        try{
            var result = parser.parse(token);
            var payload = result.getPayload();
            /*
            @TODO 파싱하는 과정에서 에러 발생, parseClaimsJws -> deprecated 대체 방법 찾기

            var map = new HashMap<String ,Object>();
            var fields = payload.getClass().getDeclaredFields();
            for(Field field : fields) {
                log.info("@FIELD: {} {}",field.getName(),field.get(field.getName()) );
                field.setAccessible(true);
                map.put(field.getName(), field.get(field.getName()));
            }
            return map;
            */
            var claims = parser.parseClaimsJws(token).getBody();
            // 클레임을 Map으로 변환하여 반환
            Map<String, Object> claimsMap = new HashMap<>(claims);

            return claimsMap;

        }catch (Exception e){
            if(e instanceof SignatureException){
                // 토큰이 유효하지 않을때
                throw new ApiException(TokenErrorCode.INVALID_TOKEN, e);
            }
            else if(e instanceof ExpiredJwtException){
                //  만료된 토큰
                throw new ApiException(TokenErrorCode.EXPIRED_TOKEN, e);
            }
            else{
                // 그외 에러
                throw new ApiException(TokenErrorCode.TOKEN_EXCEPTION, e);
            }
        }
    }
}



