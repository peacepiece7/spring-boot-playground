package org.delivery.api.common.exception;

import org.delivery.api.common.error.ErrorCodeIfs;

// errorCodeIfs, errorDescription 두 변수의 getter 를 구현하라는 인터페이스
public interface ApiExceptionIfs {

    ErrorCodeIfs getErrorCodeIfs();
    String getErrorDescription();
}
