package com.delivery.apigw.route

import org.springframework.cloud.gateway.route.RouteLocator
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder
import org.springframework.context.annotation.Configuration

// application.yaml 에 적어도 되고 설정이 많으면 여기다 적음 됨, 같이 쓸 수 있음
@Configuration
class RouteConfig() {

    fun gatewayRoutes(builder: RouteLocatorBuilder): RouteLocator {
        return builder.routes()
            .route { spec ->
                spec.order(-1)  // 우선순위
                spec.path(
                    "/service-api/api/**" // 매칭할 주소
                ).filters { filterSpec ->
// filterSpec.filter(serviceApiPrivateFilter.apply(ServiceApiPrivateFilter.Config()))  // 필터 지정
                    filterSpec.rewritePath("/service-api(?<segment>/?.*)", "\${segment}")
                }.uri(
                    "http://localhost:8080" // 라우팅할 주소
                )
            }
            .build()

    }
}