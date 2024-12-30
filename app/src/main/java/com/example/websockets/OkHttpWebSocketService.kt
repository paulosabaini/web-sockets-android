package com.example.websockets

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.WebSocket
import okhttp3.WebSocketListener

class OkHttpWebSocketService : WebSocketService {
    private val _isConnected = MutableStateFlow(false)
    override val isConnected = _isConnected.asStateFlow()

    private val _messages = MutableStateFlow<List<Pair<Boolean, String>>>(emptyList())
    override val messages = _messages.asStateFlow()

    private val okHttpClient = OkHttpClient()
    private var webSocket: WebSocket? = null

    private val webSocketListener = object : WebSocketListener() {
        override fun onOpen(webSocket: WebSocket, response: Response) {
            super.onOpen(webSocket, response)
            _isConnected.value = true
            webSocket.send("Android Client Connected")
        }

        override fun onMessage(webSocket: WebSocket, text: String) {
            super.onMessage(webSocket, text)
            _messages.update {
                it + (false to text)
            }
        }

        override fun onClosing(webSocket: WebSocket, code: Int, reason: String) {
            super.onClosing(webSocket, code, reason)
        }

        override fun onClosed(webSocket: WebSocket, code: Int, reason: String) {
            super.onClosed(webSocket, code, reason)
            _isConnected.value = false
        }

        override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
            super.onFailure(webSocket, t, response)
        }
    }

    override fun connect() {
        val request = Request.Builder().url("https://echo.websocket.org/").build()
        webSocket = okHttpClient.newWebSocket(request, webSocketListener)
    }

    override fun disconnect() {
        webSocket?.close(1000, "Android Client Disconnected")
    }

    override fun shutDown() {
        okHttpClient.dispatcher.executorService.shutdown()
    }

    override fun sendMessage(message: String) {
        if (_isConnected.value) {
            webSocket?.send(message)
            _messages.update {
                it + (true to message)
            }
        }
    }
}