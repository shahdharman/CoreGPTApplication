package com.example.coregpt.screens

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.coregpt.R
import com.example.coregpt.models.quesScreenModel.QuesDataModel
import com.example.coregpt.models.quesScreenModel.cnQA
import com.example.coregpt.models.quesScreenModel.dbmsOA
import com.example.coregpt.models.quesScreenModel.oopsQA
import com.example.coregpt.models.quesScreenModel.osQA
import com.example.coregpt.navigation.NavigationScreen
import com.example.coregpt.util.uiUtil.MyTopAppBar


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuestionScreen(navController: NavController,id: Int? =1 ) {

    var title: Int

    val quesList: List<QuesDataModel> = when (id) {
        1 -> {
//            Log.d("operatingSystem", "$id")
            title = R.string.QuesOSTopic
            osQA()
        }

        2 -> {
//            Log.d("oops", "$id")
            title = R.string.QuesOOPSTopic
            oopsQA()
        }

        3 -> {
//            Log.d("dbms", "$id")
            title = R.string.QuesDBMSTopic
            dbmsOA()
        }

        4 -> {
//            Log.d("network", "$id")
            title = R.string.QuesCNTopic
            cnQA()
        }

        else -> {

            title = R.string.QuesOSTopic
            osQA()
        }
    }

    Scaffold(
        containerColor = Color.Transparent,
        topBar = {
            MyTopAppBar(screenName = title, icon = Icons.Default.ArrowBack, color = Color.Black ) {
                navController.popBackStack()
            }

        }
    ) {
        Surface(
            modifier = Modifier
                .padding(it)
                .fillMaxSize(),
            color = Color(238, 238, 238)

        ) {

            LazyColumn {

              items(quesList){ques->
                  QuestionItem(ques = ques){
                      navController.navigate(route = NavigationScreen.AnswerScreen.withArgs(ques.ansId))

                  }
              }

            }
        }

    }


}


@Composable
fun QuestionItem(
    ques: QuesDataModel,
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
        color = Color(251, 251, 251),
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