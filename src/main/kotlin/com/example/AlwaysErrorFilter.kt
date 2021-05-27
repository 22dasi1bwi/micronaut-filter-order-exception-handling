package com.example

import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.MutableHttpResponse
import io.micronaut.http.annotation.Filter
import io.micronaut.http.filter.OncePerRequestHttpServerFilter
import io.micronaut.http.filter.ServerFilterChain
import io.micronaut.http.server.exceptions.ExceptionHandler
import org.reactivestreams.Publisher
import reactor.core.publisher.Mono
import javax.inject.Singleton

@Filter(Filter.MATCH_ALL_PATTERN)
class AlwaysErrorFilter : OncePerRequestHttpServerFilter() {

    override fun getOrder(): Int {
        return HttpServerFilterOrder.AlwaysErrorFilter
    }

    override fun doFilterOnce(request: HttpRequest<*>, chain: ServerFilterChain): Publisher<MutableHttpResponse<*>> {
        return Mono.error(AlwaysErrorException("something is wrong here"))
    }
}

class AlwaysErrorException(msg: String) : RuntimeException(msg)

@Singleton
class AlwaysErrorExceptionHandler : ExceptionHandler<AlwaysErrorException, MutableHttpResponse<*>> {

    override fun handle(request: HttpRequest<*>, exception: AlwaysErrorException): MutableHttpResponse<*> {
        return HttpResponse.serverError(exception.message).status(503)
    }

}