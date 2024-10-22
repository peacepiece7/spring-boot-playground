package org.delivery.common.api

import org.delivery.common.error.ErrorCode
import org.delivery.common.error.ErrorCodeIfs

// static 선언하기
// 1. global 선언
// 2. companion object
data class Result(
    val resultCode: Int? = null,
    val resultMessage: String? = null,
    val resultDescription: String? = null
) {

    companion object {

        fun OK(): Result {
            return Result(
                resultCode = ErrorCode.OK.getErrorCode(),
                resultMessage = ErrorCode.OK.getDescription(),
                resultDescription = "성공"
            )
        }

        fun ERROR(errorCodeIfs: ErrorCodeIfs): Result {
            return Result(
                resultCode = errorCodeIfs.getErrorCode(),
                resultMessage = errorCodeIfs.getDescription(),
                resultDescription = "에러발생"
            )
        }

        fun ERROR(errorCodeIfs: ErrorCodeIfs, tx: Throwable): Result {
            return Result(
                resultCode = errorCodeIfs.getErrorCode(),
                resultMessage = errorCodeIfs.getDescription(),
                resultDescription = tx.localizedMessage
            )
        }

        fun ERROR(errorCodeIfs: ErrorCodeIfs, description: String): Result {
            return Result(
                resultCode = errorCodeIfs.getErrorCode(),
                resultMessage = errorCodeIfs.getDescription(),
                resultDescription = description
            )
        }
    }
}