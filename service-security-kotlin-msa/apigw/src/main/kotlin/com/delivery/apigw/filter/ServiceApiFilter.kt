package com.delivery.apigw.filter

import com.delivery.apigw.common.Log
import org.springframework.cloud.gateway.filter.GatewayFilter
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory
import org.springframework.stereotype.Component

// string boot 의 filter 같은 기능
@Component
class ServiceApiFilter : AbstractGatewayFilterFactory<ServiceApiFilter.Config>(Config::class.java) {

    companion object : Log

    class Config

    override fun apply(config: Config?): GatewayFilter {
        return GatewayFilter { exchange, chain ->
            val uri = exchange.request.uri

            log.info("service api public filter route uri: {}", uri)

            val mono = chain.filter(exchange)

            mono
        }
    }
}