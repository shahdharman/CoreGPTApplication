package com.example.coregpt.models.homeScreenModel

import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.coregpt.R

data class HomeScreenModel(
    val id: Int,
    val title: Int,
    @ColorRes val color : Int,
    @DrawableRes val image: Int,
    @StringRes val description : Int
)

fun getHomeScreenList():List<HomeScreenModel>{
    return listOf(
        HomeScreenModel(
            id = 1,
            title = R.string.topQnA,
            color = R.color.osColor,
            image = R.drawable.qna,
            description = R.string.topQnaDesc
        ),
        HomeScreenModel(
            id = 2,
            title = R.string.myNotes,
            color = R.color.dbmsColor,
            image = R.drawable.mynotes,
            description = R.string.myNotesDesc
        ),
        HomeScreenModel(
            id = 3,
            title = R.string.coreGPT,
            color = R.color.oopscolor,
            image = R.drawable.bot2,
            description = R.string.coreGPTDesc
        ),
        HomeScreenModel(
            id = 4,
            title = R.string.doubt,
            color = R.color.cnColor,
            image = R.drawable.chat,
            description = R.string.chatDesc
        )
    )
}
