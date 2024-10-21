package org.delivery.storeadmin.domain.userorder.consumer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.delivery.common.message.model.UserOrderMessage;
import org.delivery.storeadmin.domain.userorder.business.UserOrderBusiness;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserOrderConsumer {

    private final UserOrderBusiness userOrderBusiness;

    @RabbitListener(queues = "delivery.queue")
    public void userOrderConsumer(
            UserOrderMessage userOrderMessage
    ) {
        try {
            log.info("🐇(RabbitMq)🐇 userOrderMessage: {}", userOrderMessage);

            if (userOrderMessage.getUserOrderId() == null) throw new RuntimeException("주문 정보를 찾을 수 없습니다.");

            userOrderBusiness.pushUserOrder(userOrderMessage);
        } catch (RuntimeException error) {
            log.error("🐇(RabbitMq)🐇 런타임 예외 발생! Error: {}", error.getMessage(), error);
        }
    }
}
