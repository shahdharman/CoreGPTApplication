package com.example.coregpt.screens

import android.annotation.SuppressLint
import android.util.Log
import androidx.annotation.RawRes
import androidx.annotation.StringRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.coregpt.R


data class QuesDataModel1(

    @StringRes val question: Int,
    @RawRes val ansId: Int,

    )


fun osQA(): List<QuesDataModel1> {
    return listOf(
        QuesDataModel1(
            question = R.string.osQues01,
            ansId = R.raw.os1
        ),
        QuesDataModel1(
            question = R.string.osQues02,
            ansId = R.raw.os2
        ),
        QuesDataModel1(
            question = R.string.osQues03,
            ansId = R.raw.os3
        ),
        QuesDataModel1(
            question = R.string.osQues04,
            ansId = R.raw.os4
        ),
        QuesDataModel1(
            question = R.string.osQues05,
            ansId = R.raw.os5
        ),
        QuesDataModel1(
            question = R.string.osQues06,
            ansId = R.raw.os6
        ),
        QuesDataModel1(
            question = R.string.osQues07,
            ansId = R.raw.os7
        ),
        QuesDataModel1(
            question = R.string.osQues08,
            ansId = R.raw.os8
        ),
        QuesDataModel1(
            question = R.string.osQues09,
            ansId = R.raw.os9
        )
    )

    /* TODO */
}




@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun QuestionScreen1() {
    val quesList: List<QuesDataModel1> = osQA()
    var searchText by remember { mutableStateOf("") }

    val filteredList = if (searchText.isNotBlank()) {
        quesList.filter { ques ->
            stringResource(ques.question).contains(searchText, ignoreCase = true)
        }
    } else {
        quesList
    }

    Column(horizontalAlignment = Alignment.CenterHorizontally,modifier = Modifier.background(Color.White).fillMaxSize()) {
        OutlinedTextField(
            value = searchText,
            onValueChange = { searchText = it },
            modifier = Modifier
                .padding(8.dp)
                .height(55.dp)
                .fillMaxWidth(),
            label = { Text(text = "Search") },
            trailingIcon = {
                Icon(imageVector = Icons.Outlined.Search, contentDescription ="Search" )
            },
            shape = RoundedCornerShape(25.dp),
            textStyle = TextStyle(fontSize = 13.sp)
        )

        LazyColumn {
            items(filteredList) { ques ->
                QuestionItem1(ques = ques) {
                    // Item click logic here
                    Log.d("Question", "sdsf")
                }
            }
        }
    }
}



@Composable
fun QuestionItem1(
    ques: QuesDataModel1,
    modifier: Modifier = Modifier,
    onQuesClick: (Int) -> Unit
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 10.dp, top = 5.dp, bottom = 5.dp, end = 10.dp)
            .clickable {
                onQuesClick(ques.ansId)
            },
        color = Color(0xfffff0e8),
        shadowElevation = 2.dp,
        shape = RoundedCornerShape(5.dp),
        border = BorderStroke(1.dp, Color.LightGray)
    ) {
        Row()
        {
            Icon(
                painter = painterResource(id = R.drawable.outline_arrow_right_24),
                contentDescription = "icon",
                modifier = Modifier
                    .padding(start = 5.dp, top = 10.dp)
                    .size(20.dp)
            )

            Text(
                text = stringResource(id = ques.question),
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Monospace,
                textAlign = TextAlign.Start,
                lineHeight = 18.sp,
                modifier = Modifier.padding(10.dp)
            )
        }

    }
}