package com.example.coregpt.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.coregpt.models.homeScreenModel.getHomeScreenList
import com.example.coregpt.screens.QuestionScreen
import com.example.coregpt.screens.chatBotScreens.ChatBotScreen
import com.example.coregpt.screens.doubtScreens.DoubtScreen
import com.example.coregpt.screens.homeScreen.HomeScreen
import com.example.coregpt.screens.mynotesScreens.MyNotesScreen
import com.example.coregpt.screens.topQnAScreens.AnswerScreen
import com.example.coregpt.screens.topicScreen.TopicScreen
import com.example.coregpt.viewmodel.CoreGPTViewModel

@Composable
fun StarApp(coreGPTViewModel: CoreGPTViewModel) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = NavigationScreen.HomeScreen.route) {

//Home Screen
        composable(route = NavigationScreen.HomeScreen.route) {
            HomeScreen(navController,homeItemList = getHomeScreenList())
        }

//Top QnA Screen
        composable(route = NavigationScreen.TopQnA.route){
            TopicScreen(navController = navController)
        }

//Question Screen
        composable(route = NavigationScreen.QuestionScreen.route + "/{id}",
        arguments = listOf(
            navArgument("id"){
                type = NavType.IntType
            }
        )
        ){backStackEntry ->
            QuestionScreen(navController,backStackEntry.arguments?.getInt("id"))
        }


//AnswerScreen
        composable(route = NavigationScreen.AnswerScreen.route + "/{ansId}",
            arguments = listOf(
                navArgument("ansId"){
                    type = NavType.IntType
                }
            )
        )
        {backStackEntry->
            AnswerScreen(ansId = backStackEntry.arguments?.getInt("ansId")){
                navController.popBackStack()
            }
        }

//CoreGPT Screen
        composable(route = NavigationScreen.CoreGPT.route){
            ChatBotScreen(coreGPTViewModel){
                navController.popBackStack()
            }
        }

//Note Screen
        composable(route = NavigationScreen.NoteScreen.route)
        {
            MyNotesScreen(coreGPTViewModel = coreGPTViewModel){
                navController.popBackStack()
            }
        }

//Doubt Screen
        composable(route = NavigationScreen.DoubtScreen.route)
        {
            DoubtScreen(coreGPTViewModel = coreGPTViewModel,navController)
        }

    }
}