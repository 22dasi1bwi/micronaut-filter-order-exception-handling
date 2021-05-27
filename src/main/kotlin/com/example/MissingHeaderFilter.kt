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
class MissingHeaderFilter : OncePerRequestHttpServerFilter() {

    override fun getOrder(): Int {
        return HttpServerFilterOrder.MissingHeaderFilter
    }

    override fun doFilterOnce(request: HttpRequest<*>, chain: ServerFilterChain): Publisher<MutableHttpResponse<*>> {
        return if (!request.headers.contains("my-header")){
            Mono.error(MissingHeaderException("my-header is missing"))
        } else {
            chain.proceed(request)
        }
    }
}

class MissingHeaderException(msg: String) : RuntimeException(msg)

@Singleton
class MissingHeaderExceptionHandler : ExceptionHandler<MissingHeaderException, MutableHttpResponse<*>> {

    override fun handle(request: HttpRequest<*>, exception: MissingHeaderException): MutableHttpResponse<*> {
        return HttpResponse.badRequest(exception.message).status(456)
    }
}
