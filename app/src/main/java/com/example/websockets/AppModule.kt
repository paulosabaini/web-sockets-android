package com.example.websockets


import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {
    singleOf(::OkHttpWebSocketService) { bind<WebSocketService>() }
    viewModelOf(::WebSocketViewModel)
}