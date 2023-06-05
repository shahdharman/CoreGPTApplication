package com.example.coregpt.viewmodel

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coregpt.models.networkModel.Message
import com.example.coregpt.models.networkModel.OpenAIRequestBody
import com.example.coregpt.repository.NetworkRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject


@HiltViewModel
class CoreGPTViewModel @Inject constructor(
    private val repository: NetworkRepository,
    private val context: Context
) : ViewModel() {
    val messages = mutableStateListOf<Message>()

    fun sendMessage(text: String, isUser: Boolean = true) {
        val message = Message(text, "user")
        messages.add(message)

        if (isUser) {
            viewModelScope.launch {
                try {
                    val response =
                        repository.generateResponse(OpenAIRequestBody(messages = messages))
                    val generateMessage = response.choices.first().message
                    messages.add(generateMessage)
                }catch (e : Exception)
                {
                    val errorMessage = "Something went wrong while fetching response"
                    showToast(errorMessage)
                }

            }

        }


    }

    fun showToast(message: String) {
        // Show toast message here
        // Replace `context` with your actual context reference
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }


}