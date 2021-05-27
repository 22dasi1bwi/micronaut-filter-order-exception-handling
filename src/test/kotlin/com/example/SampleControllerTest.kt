package com.example

import io.micronaut.context.annotation.Property
import io.micronaut.runtime.server.EmbeddedServer
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import io.restassured.RestAssured
import io.restassured.builder.RequestSpecBuilder
import org.junit.jupiter.api.Test
import javax.inject.Inject

@MicronautTest
@Property(name = "micronaut.security.enabled", value = "true")
class SampleControllerTest {

    @Inject
    lateinit var server : EmbeddedServer

    @Test
    fun `returns 503 of AlwaysErrorExceptionHandler`() {
        RestAssured.requestSpecification = RequestSpecBuilder().setBaseUri(server.uri).build()

        RestAssured.given().header("my-header", "foo").get("sample").then().statusCode(503)
    }

    @Test
    fun `returns 456 of MissingHeaderExceptionHandler`() {
        RestAssured.requestSpecification = RequestSpecBuilder().setBaseUri(server.uri).build()

        RestAssured.given().get("sample").then().statusCode(456)
    }
}
