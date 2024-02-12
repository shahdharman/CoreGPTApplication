package com.example.coregpt.models.quesScreenModel

import androidx.annotation.RawRes
import androidx.annotation.StringRes
import com.example.coregpt.R


data class QuesDataModel(

    @StringRes val question: Int,
    @RawRes val ansId: Int,

    )


fun osQA(): List<QuesDataModel> {
    return listOf(
        QuesDataModel(
            question = R.string.osQues01,
            ansId = R.raw.os1
        ),
        QuesDataModel(
            question = R.string.osQues02,
            ansId = R.raw.os2
        ),
        QuesDataModel(
            question = R.string.osQues03,
            ansId = R.raw.os3
        ),
        QuesDataModel(
            question = R.string.osQues04,
            ansId = R.raw.os4
        ),
        QuesDataModel(
            question = R.string.osQues05,
            ansId = R.raw.os5
        ),
        QuesDataModel(
            question = R.string.osQues06,
            ansId = R.raw.os6
        ),
        QuesDataModel(
            question = R.string.osQues07,
            ansId = R.raw.os7
        ),
        QuesDataModel(
            question = R.string.osQues08,
            ansId = R.raw.os8
        ),
        QuesDataModel(
            question = R.string.osQues09,
            ansId = R.raw.os9
        ),
        QuesDataModel(
            question = R.string.osQues10,
            ansId = R.raw.os10
        ),
        QuesDataModel(
            question = R.string.osQues11,
            ansId = R.raw.os10
        ),
        QuesDataModel(
            question = R.string.osQues12,
            ansId = R.raw.os12
        ),
        QuesDataModel(
            question = R.string.osQues13,
            ansId = R.raw.os13
        ),
        QuesDataModel(
            question = R.string.osQues14,
            ansId = R.raw.os14
        )
    )

    /* TODO */
}

fun cnQA(): List<QuesDataModel> {
    return listOf(
        QuesDataModel(
            question = R.string.cn01,
            ansId = R.raw.cn01
        ),
        QuesDataModel(
            question = R.string.cn02,
            ansId = R.raw.cn02
        ),
        QuesDataModel(
            question = R.string.cn03,
            ansId = R.raw.cn03
        ),
        QuesDataModel(
            question = R.string.cn04,
            ansId = R.raw.cn04
        ),
        QuesDataModel(
            question = R.string.cn05,
            ansId = R.raw.cn05
        ),
        QuesDataModel(
            question = R.string.cn06,
            ansId = R.raw.cn06
        ),
        QuesDataModel(
            question = R.string.cn07,
            ansId = R.raw.cn07
        ),
        QuesDataModel(
            question = R.string.cn08,
            ansId = R.raw.cn08
        ),
        QuesDataModel(
            question = R.string.cn09,
            ansId = R.raw.cn09
        ),
        QuesDataModel(
            question = R.string.cn10,
            ansId = R.raw.cn10
        ),
        QuesDataModel(
            question = R.string.cn11,
            ansId = R.raw.cn11
        ),
        QuesDataModel(
            question = R.string.cn12,
            ansId = R.raw.cn12
        ),
        QuesDataModel(
            question = R.string.cn13,
            ansId = R.raw.cn13
        ),
        QuesDataModel(
            question = R.string.cn14,
            ansId = R.raw.cn14
        ),
        QuesDataModel(
            question = R.string.cn15,
            ansId = R.raw.cn15
        ),
        QuesDataModel(
            question = R.string.cn16,
            ansId = R.raw.cn16
        ),
        QuesDataModel(
            question = R.string.cn17,
            ansId = R.raw.cn17
        ),
        QuesDataModel(
            question = R.string.cn18,
            ansId = R.raw.cn18
        ),
        QuesDataModel(
            question = R.string.cn19,
            ansId = R.raw.cn19
        )
        )

    /* TODO */
}

fun dbmsOA(): List<QuesDataModel> {
    return listOf(
        QuesDataModel(
            question = R.string.dbms01,
            ansId = R.raw.dbms01
        ),
        QuesDataModel(
            question = R.string.dbms02,
            ansId = R.raw.dbms02
        ),
        QuesDataModel(
            question = R.string.dbms03,
            ansId = R.raw.dbms03
        ),
        QuesDataModel(
            question = R.string.dbms04,
            ansId = R.raw.dbms04
        ),
        QuesDataModel(
            question = R.string.dbms05,
            ansId = R.raw.dbms05
        ),
        QuesDataModel(
            question = R.string.dbms06,
            ansId = R.raw.dbms06
        ),
        QuesDataModel(
            question = R.string.dbms07,
            ansId = R.raw.dbms07
        ),
        QuesDataModel(
            question = R.string.dbms08,
            ansId = R.raw.dbms08
        ),
        QuesDataModel(
            question = R.string.dbms09,
            ansId = R.raw.dbms09
        )
    )

    /* TODO */
}

fun oopsQA(): List<QuesDataModel> {
    return listOf(
        QuesDataModel(
            question = R.string.oopsQues01,
            ansId = R.raw.oops1
        ),
        QuesDataModel(
            question = R.string.oopsQues02,
            ansId = R.raw.oops2
        ),
        QuesDataModel(
            question = R.string.oopsQues03,
            ansId = R.raw.oops3
        ),
        QuesDataModel(
            question = R.string.oopsQues04,
            ansId = R.raw.oops4
        ),
        QuesDataModel(
            question = R.string.oopsQues05,
            ansId = R.raw.oops5
        ),
        QuesDataModel(
            question = R.string.oopsQues06,
            ansId = R.raw.oops6
        ),
        QuesDataModel(
            question = R.string.oopsQues07,
            ansId = R.raw.oops7
        ),
        QuesDataModel(
            question = R.string.oopsQues08,
            ansId = R.raw.oops8
        ),
        QuesDataModel(
            question = R.string.oopsQues09,
            ansId = R.raw.oops9
        ),
        QuesDataModel(
            question = R.string.oopsQues10,
            ansId = R.raw.oops10
        ),
        QuesDataModel(
            question = R.string.oopsQues11,
            ansId = R.raw.oops11
        ),
        QuesDataModel(
            question = R.string.oopsQues12,
            ansId = R.raw.oops12
        ),
        QuesDataModel(
            question = R.string.oopsQues13,
            ansId = R.raw.oops13
        ),
        QuesDataModel(
            question = R.string.oopsQues14,
            ansId = R.raw.oops14
        ),
        QuesDataModel(
            question = R.string.oopsQues15,
            ansId = R.raw.oops15
        ),
        QuesDataModel(
            question = R.string.oopsQues16,
            ansId = R.raw.oops16
        )
    )

    /* TODO */
}