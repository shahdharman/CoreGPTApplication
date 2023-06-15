package com.example.coregpt.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.outlined.Send
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.coregpt.R
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

private val BotChatBubbleShape = RoundedCornerShape(0.dp, 8.dp, 8.dp, 8.dp)
private val AuthorChatBubbleShape = RoundedCornerShape(8.dp, 0.dp, 8.dp, 8.dp)
val message = mutableStateOf("")

@Preview
@Composable
fun MainScreen() {
    val context = LocalContext.current
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween
    )
    {
        TopBarSection(
            username = "Bot",
            profile = painterResource(id = R.drawable.bot2),
            isOnline = true
        )
        ChatSection(Modifier.weight(1f))
        MessageSection()

    }


}
@Preview
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MessageSection() {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(10.dp)
    ) {
        OutlinedTextField(
            placeholder = {
                Text(text = "Message...")
            },
            value = message.value,
            onValueChange = { message.value = it },
            shape = RoundedCornerShape(25.dp),
            trailingIcon = {
                Icon(imageVector = Icons.Outlined.Send,
                    contentDescription = "Send",
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.clickable {

                    }
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        )

    }
}

@Composable
fun TopBarSection(
    username: String,
    profile: Painter,
    isOnline: Boolean
) {
    Card(
        modifier = Modifier
            .fillMaxSize()
            .height(60.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFFAFAFA)),
        elevation = CardDefaults.cardElevation(4.dp)
    )
    {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        )
        {
            Image(
                painter = profile,
                contentDescription = "profile",
                modifier = Modifier
                    .size(42.dp)
                    .clip(CircleShape)
            )
            Spacer(modifier = Modifier.width(8.dp))

            Column()
            {
                Text(text = username, fontWeight = FontWeight.SemiBold)
                Text(text = if (isOnline) "Online" else "Offline", fontSize = 12.sp)
            }
        }
    }
}

@Composable
fun ChatSection(modifier: Modifier = Modifier) {
    val simpleDateFormat = SimpleDateFormat("h:mm a", Locale.ENGLISH)
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        reverseLayout = true
    ) {
        items(message_dummy) { chat ->
            MessageItem(
                messageText = chat.text,
                time = simpleDateFormat.format(chat.time),
                isOut = chat.isOut
            )
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}


@Composable
fun MessageItem(
    messageText: String?,
    time: String,
    isOut: Boolean
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = if (isOut) Alignment.End else Alignment.Start
    ) {
        if (messageText != null) {
            if (messageText != "") {
                Box(
                    modifier = Modifier
                        .background
                            (
                            if (isOut)
                                MaterialTheme.colorScheme.primary
                            else Color(0xFF616161),
                            shape = if (isOut) AuthorChatBubbleShape else BotChatBubbleShape
                        )
                        .padding(
                            top = 8.dp,
                            bottom = 8.dp,
                            start = 16.dp,
                            end = 16.dp
                        )
                ) {
                    Text(text = messageText, color = Color.White)
                }
            }
        }
        Text(
            text = time,
            fontSize = 12.sp,
            modifier = Modifier.padding(start = 8.dp)
        )
    }
}


data class Message(
    var text: String? = null,
    var recipient_id: String,
    val time: Long = Calendar.getInstance().timeInMillis,
    var isOut: Boolean = false

)

val message_dummy = listOf<Message>(
    Message(
        text = "Great",
        recipient_id = "bot",
        isOut = false
    ),
    Message(
        text = "I am good",
        recipient_id = "user",
        isOut = true
    ),
    Message(
        text = "Hi, How are you",
        recipient_id = "bot",
        isOut = false
    ),
    Message(
        text = "hi",
        recipient_id = "user",
        isOut = false
    )

)
