package com.example.coregpt.screens.topicScreen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.coregpt.R
import com.example.coregpt.models.topicModel.TopicDataModel
import com.example.coregpt.models.topicModel.getTopicList
import com.example.coregpt.navigation.NavigationScreen
import com.example.coregpt.util.uiUtil.MyTopAppBar


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopicScreen(navController: NavController)
{
    val topicList: List<TopicDataModel> = getTopicList()
    Scaffold(
        containerColor = Color.Transparent,
        topBar = {
            MyTopAppBar(screenName = R.string.topQnA)
            /* TODO
            MODIFY MORE TOPAPP BAR
            * */

        }
    )
    { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(Color.Black)
        )
        {

            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth(),
//                    .padding(start = 5.dp, end = 5.dp),
//                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(topicList){topic->

                    TopicCardItem(topic){

                        navController.navigate(route = NavigationScreen.QuestionScreen.withArgs(topic.id))
                    }


                }
            }
        }
    }

}


@Preview
@Composable
fun TopicCardItem(topicDataModel: TopicDataModel = getTopicList()[0], onTopicClicked: (Int)->Unit ={})
{
    val titleList = stringArrayResource(id = topicDataModel.title)
    Box(
        modifier = Modifier
            .padding(start = 7.dp, end = 7.dp, top = 5.dp, bottom = 5.dp)
            .height(220.dp)
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(30.dp))
            .background(colorResource(id = topicDataModel.color))
            .clickable {
                onTopicClicked(topicDataModel.id)
            }
        ,contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier.padding(start = 20.dp),
//            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ){
            Column(
                modifier = Modifier
                    .width(130.dp)
                    .padding(top = 20.dp)
            ){
                for(name in titleList)
                {
                    Text(
                        modifier = Modifier.padding(bottom = 5.dp),
                        text = name,
                        fontFamily = FontFamily.Serif,
                        color = Color.Black,
                        fontWeight = FontWeight.Bold
                    )
                }

                Text(
                    text = stringResource(id = topicDataModel.description),
                    textAlign = TextAlign.Left,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Cursive,
                    color = Color.DarkGray
                )

                Text(
                    text = stringResource(id = R.string.Practice),
                    fontWeight = FontWeight.ExtraBold,
                    fontFamily = FontFamily.Cursive,
                    modifier = Modifier.padding(top = 10.dp),
                    textAlign = TextAlign.Center,
                    fontSize = 15.sp,
                )
            }

            Image(
                painter = painterResource(id = topicDataModel.image),
                contentDescription = "Topic Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .height(350.dp)
                    .width(400.dp)
            )
        }
    }

}
