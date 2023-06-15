package com.example.coregpt.screens.topQnAScreens

import androidx.annotation.RawRes
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.coregpt.R
import com.example.coregpt.util.uiUtil.MyTopAppBar
import com.rizzi.bouquet.ResourceType
import com.rizzi.bouquet.VerticalPDFReader
import com.rizzi.bouquet.rememberVerticalPdfReaderState


@Preview
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AnswerScreen(@RawRes ansId: Int? = R.raw.os2, onTopAppBarClick: () -> Unit = {}) {

    val pdfState = ansId?.let { ResourceType.Asset(it) }?.let {
        rememberVerticalPdfReaderState(
            resource = it,
            isZoomEnable = false
        )
    }

    Scaffold(
        topBar = {
            MyTopAppBar(
                screenName = R.string.answerScreen,
                icon = Icons.Default.ArrowBack,
                color = Color.Black
            ) {
                onTopAppBarClick()
            }
        }
    ) {
        Surface(
            modifier = Modifier
                .padding(it)
                .fillMaxSize(),
            color = Color(255, 255, 234)
        )
        {
            if (pdfState != null) {
                VerticalPDFReader(state = pdfState, modifier = Modifier.padding(vertical = 10.dp))
            }

        }

    }


}