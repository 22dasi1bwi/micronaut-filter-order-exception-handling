package com.example

import io.micronaut.core.order.Ordered

object HttpServerFilterOrder {

    const val MissingHeaderFilter = Ordered.LOWEST_PRECEDENCE - 1
    const val AlwaysErrorFilter = Ordered.LOWEST_PRECEDENCE
}
