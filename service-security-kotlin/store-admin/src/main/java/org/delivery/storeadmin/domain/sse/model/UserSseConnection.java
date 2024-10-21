package org.delivery.storeadmin.domain.sse.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.delivery.storeadmin.domain.sse.connection.ifs.ConnectionPoolIfs;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;

@Slf4j
@Data
public class UserSseConnection {

    private String uniqueKey;
    private SseEmitter sseEmitter;
    private final ConnectionPoolIfs<String, UserSseConnection> connectionPoolIfs;
    private final ObjectMapper objectMapper; // 데이터를 전송할 때, ObjectMapper 거치도록하기 (config/objectmapper/ObjectMapperConfig.jav 확인

    private UserSseConnection(
            String uniqueKey,
            ConnectionPoolIfs<String, UserSseConnection> connectionConnectionPoolIfs,
            ObjectMapper objectMapper
    ) {
        this.uniqueKey = uniqueKey;
        this.sseEmitter = new SseEmitter(6000L * 60);
        this.connectionPoolIfs = connectionConnectionPoolIfs;
        this.objectMapper = objectMapper;

        this.sseEmitter.onCompletion(() -> connectionConnectionPoolIfs.onCompletionCallback(this));

        this.sseEmitter.onTimeout(this.sseEmitter::complete);

        this.sendMessage("onopen", "connect");
    }

    public static UserSseConnection connect(
            String uniqueKey,
            ConnectionPoolIfs<String, UserSseConnection> connectionConnectionPoolIfs,
            ObjectMapper objectMapper
    ) {
        return new UserSseConnection(uniqueKey, connectionConnectionPoolIfs, objectMapper);
    }

    public void sendMessage(String eventName, Object data) {
        try {
            var json = this.objectMapper.writeValueAsString(data);
            log.info("SEND A MESSAGE: {}", json);
            var event = SseEmitter.event()
                    .name(eventName)
                    .data(json);
            this.sseEmitter.send(event);
        } catch (IOException error) {
            this.sseEmitter.completeWithError(error);
        }
    }

    public void sendMessage(Object data) {
        try {
            var json = this.objectMapper.writeValueAsString(data);
            log.info("SEND A  MESSAGE: {}", json);
            var event = SseEmitter.event()
                    .data(json);
            this.sseEmitter.send(event);

            var event2 = SseEmitter.event()
                    .name("test")
                    .data(json);
            this.sseEmitter.send(event2);

        } catch (IOException error) {
            this.sseEmitter.completeWithError(error);
        }
    }
}
