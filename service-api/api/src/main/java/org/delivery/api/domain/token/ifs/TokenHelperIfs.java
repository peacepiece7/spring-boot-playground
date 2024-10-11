package org.delivery.api.domain.token.ifs;

import org.delivery.api.domain.token.model.TokenDto;

import java.util.Map;

public interface TokenHelperIfs {

    TokenDto issueAccessToken(Map<String, Object> claims);

    TokenDto issueRefreshToken(Map<String, Object> claims);

    Map<String, Object> validationTokenWithThrow(String token);
}
