package org.delivery.api.domain.token.service;

import lombok.extern.slf4j.Slf4j;
import org.delivery.api.domain.token.ifs.TokenHelperIfs;
import org.delivery.api.domain.token.model.TokenDto;
import org.delivery.common.error.ErrorCode;
import org.delivery.common.exception.ApiException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Objects;

@Slf4j
@Service
public class TokenService {
    private final TokenHelperIfs tokenHelperIfs;

    public TokenService(TokenHelperIfs tokenHelperIfs) {
        this.tokenHelperIfs = tokenHelperIfs;
    }

    public TokenDto issueAccessToken(Long userId) {
        var data = new HashMap<String, Object>();
        data.put("userId", userId);
        return tokenHelperIfs.issueAccessToken(data);
    }

    public TokenDto issueRefreshToken(Long userId) {
        var data = new HashMap<String, Object>();
        data.put("userId", userId);
        return tokenHelperIfs.issueRefreshToken(data);
    }

    public Long validationToken(String token) {
        var map = tokenHelperIfs.validationTokenWithThrow(token);

        var userId = map.get("userId");
        Objects.requireNonNull(userId, () -> {
            throw new ApiException(ErrorCode.NULL_POINT);
        });

        return Long.parseLong(userId.toString());
    }
}
