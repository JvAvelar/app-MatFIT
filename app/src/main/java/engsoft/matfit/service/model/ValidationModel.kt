package com.devmasterteam.tasks.service.model

class ValidationModel(message: String = "") {

    private var status: Boolean = true
    private var validateMessage: String = ""

    init {
        if (message != "") {
            validateMessage = message
            status = false
        }
    }

    fun status() = status
    fun message() = validateMessage
}