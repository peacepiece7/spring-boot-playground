package org.delivery.storeadmin.domain.sse.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.delivery.storeadmin.domain.authorization.model.UserSession;
import org.delivery.storeadmin.domain.sse.connection.model.SseConnectionPool;
import org.delivery.storeadmin.domain.sse.model.UserSseConnection;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@RestController
@RequestMapping("/api/sse")
@RequiredArgsConstructor
public class SeeApiController {

    // thread-safe map
    private static final Map<String, SseEmitter> userConnection = new ConcurrentHashMap<>();
    private final SseConnectionPool sseConnectionPool;

    //
    private final ObjectMapper objectMapper;


    @GetMapping(value = "/connect")
    public ResponseBodyEmitter connect(
            @Parameter(hidden = true)
            @AuthenticationPrincipal UserSession userSession
    ) {
        var userSseConnection = UserSseConnection.connect(userSession.getStoreId().toString(),
                sseConnectionPool,
                objectMapper
        );

        sseConnectionPool.addSession(userSseConnection.getUniqueKey(), userSseConnection);

        return userSseConnection.getSseEmitter();
    }

    @GetMapping(value = "/push-event")
    public void pushEvent(
            @Parameter(hidden = true)
            @AuthenticationPrincipal UserSession userSession
    ) {
        var userSseConnection = sseConnectionPool.getSession(userSession.getStoreId().toString());
        userSseConnection.sendMessage("Hello, world!");

    }


    /**
     * @deprecated sse 연결을 어떻게 하는지 보려고 남겨둔 코드입니다. 필요 없으면 제거 합시다
     */
    @Deprecated(forRemoval = true)
    @GetMapping(value = "/before-connect", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public ResponseBodyEmitter beforeConnect(
            @Parameter(hidden = true)
            @AuthenticationPrincipal UserSession userSession
    ) {


        var emitter = new SseEmitter(1000L * 60); // reconnect time

        userConnection.put(userSession.getUserId().toString(), emitter);
        emitter.onTimeout(() -> {
            log.info("on timeout");
            // 클라이언트와 타임아웃이 일어났을 때
            emitter.complete();
        });
        emitter.onCompletion(() -> {
            log.info("on completion");
            // 클라이언트와 연결이 종료 됐을 때 하는 작업
            userConnection.remove(userSession.getUserId().toString());
        });

        var event = SseEmitter
                .event() // default event.onmessage
                .name("onopen");
        try {
            emitter.send(event);
        } catch (IOException e) {
            emitter.completeWithError(e);
        }
        return emitter;
    }

    /**
     * @deprecated sse 연결을 어떻게 하는지 보려고 남겨둔 코드입니다. 필요 없으면 제거 합시다
     */
    @Deprecated(forRemoval = true)
    @GetMapping("/before-push-event")
    public void beforePushEvent(
            @Parameter(hidden = true)
            @AuthenticationPrincipal UserSession userSession
    ) {
        var emitter = userConnection.get(userSession.getUserId().toString());
        var event = SseEmitter.event().data("hello");
        try {
            emitter.send(event);
        } catch (IOException e) {
            emitter.completeWithError(e);
        }
    }
}
