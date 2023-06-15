package com.example.coregpt.screens.chatBotScreens


import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.coregpt.R
import com.example.coregpt.util.uiUtil.MyTopAppBar
import com.example.coregpt.viewmodel.CoreGPTViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatBotScreen(viewModel: CoreGPTViewModel, onClick: ()-> Unit) {
    var inputText by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            MyTopAppBar(screenName = R.string.coreGPT, icon = Icons.Default.ArrowBack, color = Color.Black) {
                onClick()
            }
        }
    ) {


        Surface(color = Color(0xFFe1e4e8), modifier = Modifier.padding(it))
        {
            Column(modifier = Modifier.fillMaxSize()) {
                LazyColumn(
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 5.dp, bottom = 15.dp),
                    reverseLayout = true
                ) {
                    items(viewModel.messages.reversed()) { message ->
                        if (message.isUser) {
                            MessageBubble(message.content, true)
                        } else {
                            MessageBubble(message.content, false)
                        }
                    }
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, bottom = 16.dp)
                ) {
                    OutlinedTextField(
                        value = inputText,
                        onValueChange = { inputText = it },
                        modifier = Modifier.weight(1f),
                        colors = TextFieldDefaults.textFieldColors(),
                        label = { Text("Type a message") },
                        singleLine = false,
                        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Default),

                        )

                    IconButton(
                        onClick = {
                            inputText = inputText.trim()

                            if (inputText.isNotEmpty() && inputText.isNotBlank()) {
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
    }




}


@Composable
fun MessageBubble(text: String, isUser: Boolean) {
    val botChatBubbleShape = RoundedCornerShape(0.dp, 30.dp, 30.dp, 30.dp)
    val authorChatBubbleShape = RoundedCornerShape(30.dp, 0.dp, 30.dp, 30.dp)

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = if (isUser) Arrangement.End else Arrangement.Start
    )
    {

        if(!isUser)
        Image(
            painter = painterResource(id = R.drawable.bot),
            contentDescription = "bot",
            alignment = Alignment.CenterStart,
            modifier = Modifier.size(50.dp),
            contentScale = ContentScale.Crop

        )

        Box(
            modifier = Modifier
                .background(
                    if (isUser)
                        Color(0xFF939ca3)
                    else
                        Color(0xFF003F5c),
                    shape = if (isUser) authorChatBubbleShape else botChatBubbleShape
                )
        )
        {

            Text(
                text = text,
                modifier = Modifier.padding(16.dp),
                fontSize = 16.sp,
                color = Color.White,
                style = TextStyle(textAlign = TextAlign.Justify)
            )

        }



    }
    Spacer(modifier = Modifier.height(20.dp))

}
