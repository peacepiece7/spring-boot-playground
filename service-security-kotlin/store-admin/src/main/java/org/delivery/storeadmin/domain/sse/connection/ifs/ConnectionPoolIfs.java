package org.delivery.storeadmin.domain.sse.connection.ifs;

import java.util.List;

public interface ConnectionPoolIfs<T, R> {

    void addSession(T key, R session);

    R getSession(T uniqueKey);

    void onCompletionCallback(R session);

    List<R> getAllSessions();
}
