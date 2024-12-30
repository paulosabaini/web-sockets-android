package com.example.websockets

import kotlinx.coroutines.flow.StateFlow

interface WebSocketService {
    val isConnected: StateFlow<Boolean>
    val messages: StateFlow<List<Pair<Boolean, String>>>
    fun connect()
    fun disconnect()
    fun shutDown()
    fun sendMessage(message: String)
}
