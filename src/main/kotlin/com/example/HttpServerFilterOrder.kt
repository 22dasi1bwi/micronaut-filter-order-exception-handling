package com.example

import io.micronaut.core.order.Ordered

/** These filters are executed after Micronaut's filters were applied. */
object HttpServerFilterOrder {

    /** First executed filter. */
    const val MissingHeaderFilter = Ordered.LOWEST_PRECEDENCE - 1

    /** Second executed filter. */
    const val AlwaysErrorFilter = Ordered.LOWEST_PRECEDENCE
}
