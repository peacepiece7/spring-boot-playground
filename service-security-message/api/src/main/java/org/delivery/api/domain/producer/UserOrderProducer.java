package org.delivery.api.domain.producer;

import lombok.RequiredArgsConstructor;
import org.delivery.api.common.rabbitmq.Producer;
import org.delivery.common.message.model.UserOrderMessage;
import org.delivery.db.userorder.UserOrderEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Service
@RequiredArgsConstructor
public class UserOrderProducer {
    private final Producer producer;

    private static final String EXCHANGE = "delivery.exchange";

    private static final String ROUTE_KEY = "delivery.key";

    // 트랜잭션이 성공적으로 커밋된 후에만 비동기 작업 실행
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void sendOrder(UserOrderEntity userOrderEntity) {
        sendOrder(userOrderEntity.getId());
    }

    public void sendOrder(Long userOrderId) {
        var message = UserOrderMessage.builder()
                .UserOrderId(userOrderId)
                .build();
        producer.producer(EXCHANGE, ROUTE_KEY, message);
    }

}
