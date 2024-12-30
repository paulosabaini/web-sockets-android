package com.example.websockets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun AppScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        ConnectionStatus(connected = true)
        Spacer(Modifier.height(8.dp))
        ConnectionButtons()
        Spacer(Modifier.height(8.dp))
        Text("Echo Messages:", style = MaterialTheme.typography.titleLarge)
        Spacer(Modifier.height(8.dp))
        MessageList(modifier = Modifier.weight(1f), listOf("Hello", "World", "!"))
        Spacer(Modifier.height(8.dp))
        MessageInput() {}
    }
}

@Composable
fun MessageList(modifier: Modifier = Modifier, messages: List<String>) {
    LazyColumn(modifier = modifier) {
        items(messages) {
            Text(it, style = MaterialTheme.typography.bodyLarge)
        }
    }
}

@Composable
fun MessageInput(modifier: Modifier = Modifier, onSend: (String) -> Unit) {
    var text by remember { mutableStateOf("") }
    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        value = text,
        onValueChange = { s -> text = s },
        trailingIcon = {
            IconButton(
                onClick = {
                    onSend(text)
                    text = ""
                },
                enabled = text.isNotBlank(),
            ) { Icon(Icons.AutoMirrored.Filled.Send, contentDescription = null) }
        }
    )
}

@Composable
private fun ConnectionButtons() {
    Row(modifier = Modifier.fillMaxWidth()) {
        Button(
            onClick = {},
            modifier = Modifier.weight(1f),
            shape = ShapeDefaults.ExtraSmall,
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Blue.copy(alpha = 0.6f),
                disabledContainerColor = Color.LightGray,
                disabledContentColor = Color.Black.copy(alpha = 0.7f)
            )
        ) { Text("Connect") }
        Spacer(Modifier.width(8.dp))
        Button(
            onClick = {},
            modifier = Modifier.weight(1f),
            shape = ShapeDefaults.ExtraSmall,
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Blue.copy(alpha = 0.6f),
                disabledContainerColor = Color.LightGray,
                disabledContentColor = Color.Black.copy(alpha = 0.7f)
            )
        ) { Text("Disconnect") }
    }
}

@Composable
private fun ConnectionStatus(modifier: Modifier = Modifier, connected: Boolean) {
    Card(shape = ShapeDefaults.ExtraSmall) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(if (connected) Color.Green.copy(alpha = 0.3f) else Color.Red.copy(alpha = 0.3f))
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painterResource(if (connected) R.drawable.wifi else R.drawable.wifi_off),
                contentDescription = null
            )
            Spacer(Modifier.width(16.dp))
            Text(
                if (connected) "Connected" else "Disconnected",
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun AppScreenPreview() {
    AppScreen()
}