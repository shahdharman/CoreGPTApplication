package com.example.coregpt.navigation

sealed class NavigationScreen(val route : String)
{
    object HomeScreen : NavigationScreen("home_screen")
    object TopQnA : NavigationScreen("topQnA")
    object QuestionScreen : NavigationScreen("question_screen")
    object AnswerScreen : NavigationScreen("answer_screen")
    object NoteScreen : NavigationScreen("note_screen")
    object CoreGPT : NavigationScreen("core_gpt")
    object DoubtScreen : NavigationScreen("doubt_screen")

}
