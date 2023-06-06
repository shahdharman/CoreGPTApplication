package com.example.coregpt.viewmodel

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coregpt.database.ChatMessage
import com.example.coregpt.models.networkModel.Message
import com.example.coregpt.models.networkModel.OpenAIRequestBody
import com.example.coregpt.repository.DatabaseRepository
import com.example.coregpt.repository.NetworkRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception
import javax.inject.Inject


@HiltViewModel
class CoreGPTViewModel @Inject constructor(
    private val networkRepository: NetworkRepository,
    private val databaseRepository: DatabaseRepository,
    private val context: Context
) : ViewModel()
{
    val messages = mutableStateListOf<Message>()

    fun sendMessage(text: String, isUser: Boolean = true) {
        val message = Message(text, "user")

        val msg = listOf<Message>(
            Message(
                "$text | If you recognize this input, Give Response Short and Less than 100 words Or  just say Sorry! Not Able To Recognize it, Give Correct Input",
                "user"
            )
        )

        messages.add(message)

        if (isUser) {
            viewModelScope.launch {
                try {
                    val response =
                        networkRepository.generateResponse(OpenAIRequestBody(messages = messages))
                    val generateMessage = response.choices.first().message
                    messages.add(generateMessage)

                    val chatEntity = ChatMessage(
                        request = message.content,
                        response = generateMessage.content
                    )
                    withContext(Dispatchers.IO){
                        databaseRepository.insertChat(chatEntity)
                    }

                } catch (e: Exception) {
                    val errorMessage = "Something went wrong while fetching response"
                    showToast(errorMessage)
                }

            }

        }


    }

    private fun showToast(message: String) {
        // Show toast message here
        // Replace `context` with your actual context reference
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }


}