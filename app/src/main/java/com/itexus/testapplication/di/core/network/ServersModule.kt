package com.itexus.testapplication.di.core.network

import io.github.aakira.napier.Napier
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.plugins.observer.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import org.koin.dsl.module

internal val serversModule = module {
    single { provideKtorClient() }
}

private const val TIME_OUT = 60_000
private fun provideKtorClient(): HttpClient {
    return HttpClient(Android) {

        install(ContentNegotiation) {
            json(
                Json {
                    prettyPrint = true
                    isLenient = true
                }
            )
            engine {
                connectTimeout = TIME_OUT
                socketTimeout = TIME_OUT
            }
        }

        install(Logging) {
            logger = object : Logger {
                override fun log(message: String) {
                    Napier.v("Logger Ktor => $message")
                }

            }
            level = LogLevel.BODY
        }

        install(ResponseObserver) {
            onResponse { response ->
                Napier.d("HTTP status: ${response.status.value}")
            }
        }

        install(DefaultRequest) {
            header(HttpHeaders.ContentType, ContentType.Application.Json)
        }
    }
}