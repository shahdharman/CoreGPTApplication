package com.example.coregpt.models.quesScreenModel

import androidx.annotation.RawRes
import androidx.annotation.StringRes
import com.example.coregpt.R


data class QuesDataModel(

    @StringRes val question: Int,
    @RawRes val ansId: Int,

    )



fun osQA(): List<QuesDataModel>{
    return listOf(
        QuesDataModel(
            question = R.string.osQues01 ,
            ansId = R.raw.os1
        ),
        QuesDataModel(
            question = R.string.osQues02 ,
            ansId = R.raw.os2
        )
    )

    /* TODO */
}

fun cnQA(): List<QuesDataModel>{
    return listOf(
        QuesDataModel(
            question = R.string.osQues01 ,
            ansId = R.raw.os1
        )
    )

    /* TODO */
}

fun dbmsOA(): List<QuesDataModel>{
    return listOf(
        QuesDataModel(

            question = R.string.osQues01 ,
            ansId = R.raw.os2
        )
    )

    /* TODO */
}

fun oopsQA(): List<QuesDataModel>{
    return listOf(
        QuesDataModel(
            question = R.string.osQues01 ,
            ansId = R.raw.os1
        )
    )

    /* TODO */
}