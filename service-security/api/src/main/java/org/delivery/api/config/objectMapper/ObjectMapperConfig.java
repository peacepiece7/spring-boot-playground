package org.delivery.api.config.objectMapper;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// object mapper 를 안만들면 스프링에서 default 로 하나 만들어 줌 만들었으니까 내것으로 적용될 것
@Configuration
public class ObjectMapperConfig {

    @Bean
    public ObjectMapper objectMapper() {
        // 존슨을 자바 객체로 변환하거나, 자바 객체를 존슨으로 변환하는 데 사용한다.
        var objectMapper = new ObjectMapper();

        // jdk 8 버전 이후에 나온 클래스, 기능을 Jackson 이 처라할 수 있도록 해준다.
        objectMapper.registerModule(new Jdk8Module());

        // Java 8의 java.time.LocalDate, java.time.LocalDateTime 등을 Jackson 이 처리할 수 있도록 해준다.
        objectMapper.registerModule(new JavaTimeModule());

        // JSON 데이터에 포함된 예상하지 못한 필드를 처리하는 방식을 지정한다.
        // 정의되지 않은 필드를 만나면 Exception 발생이 default, false 시 이를 무시하고 de/serialization 수행
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        // Getter 가 없는 빈 클래스를 직렬화 시 예외 발생이 default, false 시 이를 무시하고 de/serialization 수행, 모든 필드가 null 일 경우 사용됨
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false); //

        // Jackson 은 TimeStamp 로 직렬화 하는게 default, disable 시 ISO 8601 형식으로 직렬화 됨, LocalDate, LocalDateTime 을 가독성 좋게 변경할 떄 사용
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        // Java 객채 필드의 이름을 JSON 필드 이름으로 변환할 떄 Snake Case 스타일 지정
        // 요거 있으면 @JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class) 안붙여도 됨
        objectMapper.setPropertyNamingStrategy(new PropertyNamingStrategies.SnakeCaseStrategy());

        return objectMapper;
    }
}
