package org.delivery.api.config.jpa.swagger;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.core.jackson.ModelResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    // ObjectMapperConfig.java에 정의한 objectMapper 메서드가 실행되면 리턴값이 요기 파라메터(objectMapper)로 주어짐
    @Bean
    public ModelResolver modelResolver (ObjectMapper objectMapper) {
        return new ModelResolver(objectMapper);
    }
}
