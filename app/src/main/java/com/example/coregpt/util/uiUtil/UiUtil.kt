package com.example.coregpt.util.uiUtil

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.coregpt.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopAppBar(modifier: Modifier = Modifier, screenName: Int, icon: ImageVector = Icons.Default.Menu, color: Color = Color.White){

    TopAppBar(
        title = {
            Text(text = stringResource(id = screenName))
        },
        navigationIcon = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = icon,
                    contentDescription = "Top App Bar Icon",
                    tint = Color.Black,
                    modifier = Modifier.size(35.dp)
                )
            }
        },
        modifier = modifier,
        colors = TopAppBarDefaults.mediumTopAppBarColors(color)
    )
}


/*
            TopAppBar(
                title = {
                    Text(text = stringResource(id = R.string.TopInterviewSceen))
                },
                modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp),
                navigationIcon = {
                    IconButton(
                        onClick = { /*TODO*/ },
                        modifier = Modifier
                            .clip(shape = RoundedCornerShape(25.dp))
                            .background(Color.Black)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Menu,
                            contentDescription = "Menu Icon",
                            tint = Color.White
                        )

                    }

                },
                colors = TopAppBarDefaults.mediumTopAppBarColors(Color.Transparent)
            )
 */