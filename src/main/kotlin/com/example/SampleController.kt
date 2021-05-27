package com.example

import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get

@Controller
class SampleController {

    @Get("/sample")
    fun sample() : String = "hello"
}