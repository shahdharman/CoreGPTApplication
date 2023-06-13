package com.example.coregpt

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import com.example.coregpt.navigation.StarApp
import com.example.coregpt.ui.theme.CoreGPTTheme
import com.example.coregpt.viewmodel.CoreGPTViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val coreViewModel = ViewModelProvider(this).get(CoreGPTViewModel::class.java) // For fragment

            StarApp(coreViewModel)
        }
    }
}

