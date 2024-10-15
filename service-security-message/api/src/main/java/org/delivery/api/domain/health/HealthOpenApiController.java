package org.delivery.api.domain.health;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.delivery.api.common.rabbitmq.Producer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/open-api")
public class HealthOpenApiController {

    private final Producer producer;

    @GetMapping("/health")
    public ResponseEntity<String> health() {
        log.info("health called");
        producer.producer("delivery.exchange", "delivery.key", "Hello, world!");
        return ResponseEntity.status(HttpStatus.OK).body("SUCCESS");
    }
}