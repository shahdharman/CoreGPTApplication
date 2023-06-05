package com.example.coregpt.screens.homeScreen

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.coregpt.models.homeScreenModel.HomeScreenModel
import com.example.coregpt.models.homeScreenModel.getHomeScreenList

@Preview
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(homeItemList : List<HomeScreenModel> = getHomeScreenList()) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "CoreGPT",
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.Monospace,
                        color = Color.White
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { /*TODO*/ })
                    {
                        Icon(
                            imageVector = Icons.Outlined.Menu,
                            contentDescription = "Main_Menu",
                            tint = Color.White,
                            modifier = Modifier.size(35.dp)
                        )
                    }
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color.Black)
            )
        }
    ) {

        Column(modifier = Modifier.padding(it)
            .background(Color.Black))
        {

            LazyColumn{
                items(homeItemList)
                {
                    HomeScreenCard(it)
                }
            }
        }



    }
}

@Preview
@Composable
fun HomeScreenCard(
    homeScreenModel: HomeScreenModel = getHomeScreenList()[0],
    onHomeItemClicked: (Int) -> Unit = {}
) {
    Box(modifier = Modifier
        .height(160.dp)
        .padding(start = 10.dp, end = 10.dp, top = 5.dp, bottom = 5.dp)
        .fillMaxWidth()
        .clip(shape = RoundedCornerShape(30.dp))
        .background(colorResource(id = homeScreenModel.color))
        .clickable {

        }
    ) {
        Row(modifier = Modifier.padding(start = 10.dp))
        {
            Column(verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .width(100.dp)
                    .padding(top = 10.dp)
            ) {
                Image(
                    painter = painterResource(id = homeScreenModel.image),
                    contentDescription = "HOme_item",
                    contentScale = ContentScale.Inside,
                    modifier = Modifier
                        .height(120.dp)
                        .width(100.dp)
                )

            }

            Column(modifier = Modifier
                .padding(top = 15.dp, start = 5.dp, end = 5.dp)
                .fillMaxWidth())
            {
                Text(
                    text = stringResource(id = homeScreenModel.title),
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.SansSerif,
                    textAlign = TextAlign.Center
                )

                Divider(thickness = 2.dp, modifier = Modifier.padding(top = 10.dp, bottom = 10.dp))

                Text(
                    text = stringResource(id = homeScreenModel.description),
                    style = MaterialTheme.typography.bodySmall,
                    textAlign = TextAlign.Justify,
                    fontFamily = FontFamily.Cursive,
                    fontWeight = FontWeight.Bold
                )
            }

        }
    }


}