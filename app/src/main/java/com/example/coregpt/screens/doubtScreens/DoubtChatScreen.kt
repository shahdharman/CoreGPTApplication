package com.example.coregpt.screens.doubtScreens

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.coregpt.R
import com.example.coregpt.database.ChatMessage
import com.example.coregpt.util.uiUtil.MyTopAppBar
import com.example.coregpt.viewmodel.CoreGPTViewModel

@Composable
fun DoubtScreen(coreGPTViewModel: CoreGPTViewModel) {
    val doubtList = coreGPTViewModel.doubtList.collectAsState().value

    ShowDoubtChat(doubts = doubtList, onRemoveDoubt = {
        coreGPTViewModel.deleteChat(it)
    })
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ShowDoubtChat(
    doubts: List<ChatMessage>,
    onRemoveDoubt: (ChatMessage) -> Unit
) {
    Scaffold(
        topBar = {
            MyTopAppBar(screenName = R.string.doubt)
        }
    )
    {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(it),
            horizontalAlignment = Alignment.CenterHorizontally,
        )
        {
            if (doubts.isNullOrEmpty()) {
                Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize())
                {
                    Text(text = "There Is No Doubt History...")
                }
            } else {
                LazyColumn {
                    items(doubts) { doubt ->
                        DoubtRow(doubt = doubt, onClick = {
                            onRemoveDoubt(doubt)
                        })

                    }
                }
            }
        }
    }


}


@Composable
fun DoubtRow(
    modifier: Modifier = Modifier,
    doubt: ChatMessage,
    onClick: (ChatMessage) -> Unit
) {
    val expanded = remember {
        mutableStateOf(false)
    }
    val context = LocalContext.current


    Card(
        modifier = modifier
            .padding(start = 10.dp, end = 10.dp, top = 5.dp, bottom = 5.dp)
            .fillMaxWidth()
            .clickable {
                expanded.value = !expanded.value

            }
            .clip(RoundedCornerShape(15.dp)),
        colors = CardDefaults.cardColors(containerColor = Color(255, 245, 157, 255)),
        elevation = CardDefaults.cardElevation(defaultElevation = 20.dp, pressedElevation = 10.dp)
    )
    {
        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(top = 0.dp, bottom = 0.dp, start = 15.dp, end = 15.dp)
        )
        {
            Text(
                text = doubt.request,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.SansSerif,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(top = 15.dp, bottom = 15.dp)

            )

            if (expanded.value) {
                Text(
                    text = doubt.response,
                    textAlign = TextAlign.Justify,
                    style = MaterialTheme.typography.bodyMedium
                )

                IconButton(onClick = {
                    onClick(doubt)
                    Toast.makeText(context, "Deleted", Toast.LENGTH_SHORT).show()

                })
                {
                    Icon(
                        imageVector = Icons.Filled.Delete,
                        contentDescription = "Delete Chat",
                        tint = Color.Red
                    )
                }
            }

        }

    }


}