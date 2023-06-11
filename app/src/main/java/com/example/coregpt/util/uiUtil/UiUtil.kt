package com.example.coregpt.util.uiUtil

import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
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


@Composable
fun selectCategory(
    selectedOption: String,
    onOptionSelected: (String) -> Unit
) {
    val radioOptions = listOf("OS", "DBMS", "OOPS", "CN")

    LazyRow {
        items(radioOptions) { option ->
            Row(
                Modifier
                    .clickable { onOptionSelected(option) },
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = (option == selectedOption),
                    onClick = { onOptionSelected(option) }
                )
                Text(
                    text = option
                )
            }
        }
    }

}


@Composable
fun provideCategory(category: String): String {
    return when (category) {
        "OS" -> "Operating System"
        "DBMS" -> "Database Management System"
        "OOPS" -> "Object Oriented Programming System"
        "CN" -> "Computer Networking"
        else -> ""
    }

}