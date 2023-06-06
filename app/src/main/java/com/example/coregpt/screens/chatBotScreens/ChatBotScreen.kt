package com.example.coregpt.screens.chatScreen



import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.coregpt.viewmodel.CoreGPTViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
/* TODO  VIEWMODEL KO HATANA H  */

fun ChatScreen(viewModel: CoreGPTViewModel) {

//    val context = LocalContext.current


    Column(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .padding(16.dp),
            reverseLayout = true
        ) {
            items(viewModel.messages.reversed()) { message ->
                if (message.isUser) {
                    MessageBubble(message.content, Alignment.End, Color.LightGray)
                } else {
                    MessageBubble(message.content, Alignment.Start, Color.Gray)
                }
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, bottom = 16.dp)
        ) {
            var inputText by remember { mutableStateOf("") }
            TextField(
                value = inputText,
                onValueChange = { inputText = it },
                modifier = Modifier.weight(1f),
                colors = TextFieldDefaults.textFieldColors(),
                label = { Text("Type a message") },
                singleLine = false,
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Default),
                keyboardActions = KeyboardActions(onAny = {
                    viewModel.sendMessage(inputText)
                    inputText = ""
                })
            )

            IconButton(
                onClick = {
                    inputText = inputText.trim()

                    if (inputText.isNotEmpty() && inputText.isNotBlank() ) {
                        viewModel.sendMessage(inputText)
                        inputText = ""
                    }

                },
            ) {
                Icon(Icons.Default.Send, contentDescription = "Send message")
            }
        }
    }
}


@Composable
fun MessageBubble(text: String, alignment: Alignment.Horizontal, color: Color) {
    val bubbleAlignment = if (alignment == Alignment.End) Alignment.End else Alignment.Start

    Surface(
        shape = MaterialTheme.shapes.medium,
        tonalElevation = 4.dp,
        modifier = Modifier
            .padding(vertical = 4.dp)
            .fillMaxWidth()
            .wrapContentWidth(bubbleAlignment),
        color = color
    ) {
        Text(
            text = text,
            modifier = Modifier.padding(16.dp),
            fontSize = 16.sp
        )
    }
}
