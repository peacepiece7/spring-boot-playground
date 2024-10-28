package org.delivery.account.domain.token.model

import java.time.LocalDateTime

data class TokenDto(
    private val token: String? = null,
    private val expiredAt: LocalDateTime? = null
) {
}