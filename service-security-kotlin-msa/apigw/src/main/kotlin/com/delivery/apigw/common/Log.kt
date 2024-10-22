package com.delivery.apigw.common

import org.slf4j.Logger
import org.slf4j.LoggerFactory

interface Log {
    // slf4j kotlin 에서 쓰려면 이렇게 해줘야함
    val log: Logger get() = LoggerFactory.getLogger(this.javaClass)
}