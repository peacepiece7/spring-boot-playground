package org.delivery.common.api

import jakarta.validation.Valid
import org.delivery.common.error.ErrorCodeIfs

data class Api<T>(
    private var result: Result? = null,

    @field:Valid // @Valid => @field:Valid
    var body: T? = null
) {
    companion object {

        @JvmStatic
        fun <T> OK(body: T): Api<T> {
            return Api(
                result = Result.OK(),
                body = body
            )
        }

        @JvmStatic
        fun <T> ERROR(result: Result): Api<T> {
            return Api(
                result = result

            )
        }

        @JvmStatic
        fun <T> ERROR(errorCodeIfs: ErrorCodeIfs): Api<T> {
            return Api(
                result = Result.ERROR(errorCodeIfs)
            )
        }

        @JvmStatic
        fun <T> ERROR(errorCodeIfs: ErrorCodeIfs, tx: Throwable): Api<T> {
            return Api(
                result = Result.ERROR(errorCodeIfs, tx)
            )
        }

        @JvmStatic
        fun <T> ERROR(errorCodeIfs: ErrorCodeIfs, description: String): Api<T> {
            return Api(
                result = Result.ERROR(errorCodeIfs, description)
            )
        }
    }
}