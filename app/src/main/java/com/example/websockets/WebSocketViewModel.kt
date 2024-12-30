package com.example.websockets

import androidx.lifecycle.ViewModel

class WebSocketViewModel(private val webSocketService: WebSocketService) : ViewModel() {
    val connectionStatus = webSocketService.isConnected
    val messages = webSocketService.messages

    override fun onCleared() {
        super.onCleared()
        webSocketService.shutDown()
    }

    fun send(message: String) {
        webSocketService.sendMessage(message)
    }

    fun connect() {
        webSocketService.connect()
    }

    fun disconnect() {
        webSocketService.disconnect()
    }
}