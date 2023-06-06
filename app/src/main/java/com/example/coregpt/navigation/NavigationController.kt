package com.example.coregpt.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.coregpt.models.homeScreenModel.getHomeScreenList
import com.example.coregpt.screens.chatScreen.ChatScreen
import com.example.coregpt.screens.doubtScreens.DoubtScreen
import com.example.coregpt.screens.homeScreen.HomeScreen
import com.example.coregpt.screens.mynotesScreens.MyNotesScreen
import com.example.coregpt.screens.topicScreen.TopicScreen
import com.example.coregpt.viewmodel.CoreGPTViewModel

@Composable
fun StarApp(coreGPTViewModel: CoreGPTViewModel) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = NavigationScreen.HomeScreen.route) {

        composable(route = NavigationScreen.HomeScreen.route) {
            HomeScreen(navController,homeItemList = getHomeScreenList())
        }

        composable(route = NavigationScreen.TopQnA.route){
            TopicScreen(navController = navController)
        }

        composable(route = NavigationScreen.CoreGPT.route){
            ChatScreen(coreGPTViewModel)
        }

        composable(route = NavigationScreen.NoteScreen.route)
        {
            MyNotesScreen()
        }
        composable(route = NavigationScreen.DoubtScreen.route)
        {
            DoubtScreen()
        }


    }
}