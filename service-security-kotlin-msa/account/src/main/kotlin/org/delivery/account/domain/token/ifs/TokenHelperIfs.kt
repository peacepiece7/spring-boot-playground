package org.delivery.account.domain.token.ifs

import org.delivery.account.domain.token.model.TokenDto

interface TokenHelperIfs {
    fun issueAccessToken(claims: Map<String, Any>): TokenDto
    fun issueRefreshToken(claims: Map<String, Any>): TokenDto
    fun validationTokenWithThrow(token: String?): Map<String, Any>
}