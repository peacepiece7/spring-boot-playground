package org.delivery.storeadmin.domain.sse.connection.model;

import lombok.extern.slf4j.Slf4j;
import org.delivery.storeadmin.domain.sse.connection.ifs.ConnectionPoolIfs;
import org.delivery.storeadmin.domain.sse.model.UserSseConnection;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Component
public class SseConnectionPool implements ConnectionPoolIfs<String, UserSseConnection> {

    private static final Map<String, UserSseConnection> connectionPool = new ConcurrentHashMap<>();

    @Override
    public void addSession(String uniqueKey, UserSseConnection session) {
        connectionPool.put(uniqueKey, session);
    }

    @Override
    public List<UserSseConnection> getAllSessions() {
        return new ArrayList<>(connectionPool.values());
    }

    @Override
    public UserSseConnection getSession(String uniqueKey) {
        return connectionPool.get(uniqueKey);
    }


    public void onCompletionCallback(UserSseConnection session) {
        log.info("call back connection pool completion: {}", session);
//        connectionPool.remove(session.getUniqueKey());
    }

}