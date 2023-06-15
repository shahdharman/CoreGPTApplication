package com.example.coregpt.models.topicModel

import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.coregpt.R

//data class TopicDataModel()
data class TopicDataModel(
    val id: Int,
    val title: Int,
    @ColorRes val color: Int,
    @DrawableRes val image: Int,
    @StringRes val description: Int
)


fun getTopicList():List<TopicDataModel>{
    return listOf(
        TopicDataModel(
            id = 1,
            title = R.string.osTopic,
            color = R.color.osColor,
            image = R.drawable.osimage,
            description = R.string.osDesc
        ),
        TopicDataModel(
            id = 3,
            title = R.string.dbmsTopic,
            color = R.color.dbmsColor,
            image = R.drawable.dbmsimage,
            description = R.string.dbmsDesc
        ),
        TopicDataModel(
            id = 2,
            title = R.string.oopsTopic,
            color = R.color.oopscolor,
            image = R.drawable.oopsimage,
            description = R.string.oopsDesc
        ),
        TopicDataModel(
            id = 4,
            title = R.string.cnTopic,
            color = R.color.cnColor,
            image = R.drawable.cnimage,
            description = R.string.cnDesc
        )
    )
}
