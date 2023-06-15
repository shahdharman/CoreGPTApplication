package com.example.coregpt.screens.doubtScreens

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.border
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.coregpt.R
import com.example.coregpt.database.ChatMessage
import com.example.coregpt.util.uiUtil.MyTopAppBar
import com.example.coregpt.viewmodel.CoreGPTViewModel

@Composable
fun DoubtScreen(coreGPTViewModel: CoreGPTViewModel, navController: NavController) {
    val doubtList = coreGPTViewModel.doubtList.collectAsState().value

    ShowDoubtChat(doubts = doubtList,
        onTopBarClick = {
            navController.popBackStack()
        },
        onRemoveDoubt = {
            coreGPTViewModel.deleteChat(it)
        })
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ShowDoubtChat(
    doubts: List<ChatMessage>,
    onTopBarClick: () -> Unit,
    onRemoveDoubt: (ChatMessage) -> Unit
) {
    Scaffold(
        topBar = {
            MyTopAppBar(
                screenName = R.string.doubt,
                icon = Icons.Default.ArrowBack,
                color = Color.Black
            )
            {
                onTopBarClick()
            }
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
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    )
                    {
                        Text(
                            text = "There Is No Doubt History...",
                            fontStyle = FontStyle.Italic,
                            fontWeight = FontWeight.Light,
                            style = MaterialTheme.typography.bodyMedium
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = "(Ask Your Doubts to ChatBot)",
                            fontStyle = FontStyle.Normal,
                            fontWeight = FontWeight.Light,
                            style = MaterialTheme.typography.bodyMedium
                        )

                    }
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
    val context = LocalContext.current
    var expanded by remember {
        mutableStateOf(false)
    }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 5.dp, end = 5.dp)
            .padding(vertical = 10.dp)
            .clip(RoundedCornerShape(10.dp))
            .border(1.dp, Color.Gray, RoundedCornerShape(10.dp))
            .clickable { expanded = !expanded },
        elevation = CardDefaults.cardElevation(2.dp),
        colors = CardDefaults.cardColors(containerColor = Color(223, 243, 200, 255))
    ) {
        Column(
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 14.dp, vertical = 6.dp)
        ) {
            Text(
                text = "Doubt: ${doubt.request}",
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Start,
                fontFamily = FontFamily.Default
            )

            if (expanded) {
                Text(
                    text = "${doubt.response}",
                    fontWeight = FontWeight.Normal,
                    textAlign = TextAlign.Justify,
                    fontFamily = FontFamily.Default,
                    lineHeight = 20.sp
                )
            } else {
                Text(
                    text = "Tap to expand...",
                    fontStyle = FontStyle.Italic,
                    style = MaterialTheme.typography.bodySmall,
                    fontWeight = FontWeight.Light
                )
            }

            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                IconButton(onClick = {
                    onClick(doubt)
                    Toast.makeText(context, "Deleted", Toast.LENGTH_SHORT).show()
                })
                {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Delete Note",
                        tint = Color(0xFFb6042a)
                    )
                }

            }

        }

    }

}