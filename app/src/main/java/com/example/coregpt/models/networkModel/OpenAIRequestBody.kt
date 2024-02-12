package com.example.coregpt.models.networkModel

data class OpenAIRequestBody(
    val model: String = "gpt-3.5-turbo-0613",
    val messages: List<Message>,
    val max_tokens: Int = 300,
    val n: Int = 1,
    val temperature: Double = 1.0
)

data class Message(val content: String, val role: String) {
    val isUser: Boolean
        get() = role == "user"
}