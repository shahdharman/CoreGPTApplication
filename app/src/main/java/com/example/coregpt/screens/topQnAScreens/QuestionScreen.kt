package com.example.coregpt.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
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
@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun QuestionScreen(navController: NavController, id: Int? = 1) {

    val title: Int

    val quesList: List<QuesDataModel> = when (id) {
        1 -> {
            title = R.string.QuesOSTopic
            osQA()
        }

        2 -> {
            title = R.string.QuesOOPSTopic
            oopsQA()
        }

        3 -> {
            title = R.string.QuesDBMSTopic
            dbmsOA()
        }

        4 -> {
            title = R.string.QuesCNTopic
            cnQA()
        }

        else -> {

            title = R.string.QuesOSTopic
            osQA()
        }
    }

    var searchText by remember { mutableStateOf("") }
    val filteredList = if ((searchText.trim()).isNotBlank()) {
        quesList.filter { ques ->
            stringResource(ques.question).contains(searchText.trim(), ignoreCase = true)
        }
    } else {
        quesList
    }

    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current

    Scaffold(
        containerColor = Color.Transparent,
        topBar = {
            MyTopAppBar(screenName = title, icon = Icons.Default.ArrowBack, color = Color.Black) {
                navController.popBackStack()
            }

        }
    ) { inner_padding ->
        Surface(
            modifier = Modifier
                .padding(inner_padding)
                .fillMaxSize(),
            color = Color(238, 238, 238)

        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .background(Color.White)
                    .fillMaxSize()
            ) {
                OutlinedTextField(
                    value = searchText,
                    onValueChange = { searchText = it },
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth() ,
                    label = { Text(text = "Search") },
                    trailingIcon = {
                        Icon(imageVector = Icons.Outlined.Search, contentDescription = "Search")
                    },
                    shape = RoundedCornerShape(25.dp),
                    textStyle = TextStyle(fontSize = 13.sp),
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            keyboardController?.hide() // Hide the keyboard
                            focusManager.clearFocus() // Remove focus from the TextField
                        }
                    )
                )


                LazyColumn {

                    items(filteredList) { ques ->
                        QuestionItem(ques = ques) {
                            navController.navigate(
                                route = NavigationScreen.AnswerScreen.withArgs(
                                    ques.ansId
                                )
                            )

                        }
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