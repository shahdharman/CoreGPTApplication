package com.example.coregpt.viewmodel

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coregpt.database.ChatMessage
import com.example.coregpt.database.MyNote
import com.example.coregpt.models.networkModel.Message
import com.example.coregpt.models.networkModel.OpenAIRequestBody
import com.example.coregpt.repository.DatabaseRepository
import com.example.coregpt.repository.NetworkRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception
import javax.inject.Inject


@HiltViewModel
class CoreGPTViewModel @Inject constructor(
    private val networkRepository: NetworkRepository,
    private val databaseRepository: DatabaseRepository,
    private val context: Context
) : ViewModel() {
    val messages = mutableStateListOf<Message>()

    private val _doubtList = MutableStateFlow<List<ChatMessage>>(emptyList())
    val doubtList = _doubtList.asStateFlow()

    private val _noteList = MutableStateFlow<List<MyNote>>(emptyList())


    init {
        viewModelScope.launch(Dispatchers.IO)
        {
            databaseRepository.getAllChat().distinctUntilChanged().collect() { listOfChat ->

                _doubtList.value = listOfChat

            }
        }
    }

    fun sendMessage(text: String, isUser: Boolean = true) {
        val message = Message(text, "user")

        val msg = listOf<Message>(
            Message(
                "$text  give output short and in 100 words",
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
                    withContext(Dispatchers.IO) {
                        databaseRepository.insertChat(chatEntity)
                    }

                } catch (e: Exception) {
                    val errorMessage = "Something went wrong while fetching response"
                    showToast(errorMessage)
                }

            }

        }
    }

    fun deleteChat(chatMessage: ChatMessage) = viewModelScope.launch {
        databaseRepository.deleteChat(chatMessage)
    }

    private fun showToast(message: String) {
        // Show toast message here
        // Replace `context` with your actual context reference
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    fun getMyNoteByCategory(category: String): StateFlow<List<MyNote>> {
        viewModelScope.launch(Dispatchers.IO) {
            databaseRepository.getMyNoteByCategory(category = category).distinctUntilChanged().collect { listofNote ->
                _noteList.value = listofNote
            }
        }
        return _noteList.asStateFlow()
    }

    fun insert(myNote: MyNote) = viewModelScope.launch {
        databaseRepository.insert(myNote)
    }

    fun update(myNote: MyNote) = viewModelScope.launch {
        databaseRepository.update(myNote)
    }

    fun delete(myNote: MyNote) = viewModelScope.launch {
        databaseRepository.delete(myNote)
    }


}