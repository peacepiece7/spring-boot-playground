package org.delivery.api.config.rabbitmq;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/*
 * queue, exchange 메서드 @Bean 으로 등록
 * queue, exchange => binding 메서드에서 연결
 * ---
 * connectionFactory, convertor => rabbitTemplate 메서드에서 연결
 * connectionFactory => application.yaml 에 정의
 * converter 메서드 @Bean 으로 등록
 */
@Configuration
public class RabbitMqConfig {
    // exchange 정의
    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange("delivery.exchange"); // exchange 이름 등록
    }

    // queue 정의
    @Bean
    public Queue queue() {
        return new Queue("delivery.queue");
    }

    // application context 안에서 알아서 directExchange, queue 를 찾아서 요기에 넣어줌
    @Bean
    public Binding binding(DirectExchange directExchange, Queue queue) {
        return BindingBuilder.bind(queue).to(directExchange).with("delivery.key");
    }

    // import 경로 확인!
    @Bean
    public RabbitTemplate rabbitTemplate(
            ConnectionFactory connectionFactory, //
            MessageConverter messageConverter
    ) {
        var rabbitTemplate = new RabbitTemplate(connectionFactory);
        // converter 가 dto 를 변환 해주는 것 처럼
        // messageConverter 도 json 을 받으면 object, object 는 json 으로 바꿔주는 역할
        rabbitTemplate.setMessageConverter(messageConverter);
        return rabbitTemplate;
    }

    // object mapper/ObjectMapperConfig.java 에서 설정한 ObjectMapper 설정이 여기 파라메터로 들어오게 된다.
    @Bean
    public MessageConverter messageConverter(ObjectMapper objectMapper) {
        return new Jackson2JsonMessageConverter(objectMapper);
    }
}
