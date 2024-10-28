package org.delivery.account.domain.token.business

import org.delivery.account.common.Log
import org.delivery.account.domain.token.controller.model.TokenValidationRequest
import org.delivery.account.domain.token.controller.model.TokenValidationResponse
import org.delivery.account.domain.token.service.TokenService
import org.delivery.common.annotation.Business

@Business
class TokenBusiness(
    private val tokenService: TokenService,
) {

    companion object : Log

    fun tokenValidation(
        tokenValidationRequest: TokenValidationRequest?,
    ): TokenValidationResponse {
        //  val token = tokenValidationRequest?.tokenDto.token ??
        log.info("token validation request businuess : {}", tokenValidationRequest)
        val result = tokenService.validationToken(tokenValidationRequest?.tokenDto.toString())
        
        return TokenValidationResponse(
            userId = result
        )
    }
}