package com.devmasterteam.tasks.service.listener

interface OnAlunoListener {
    fun onUpdate(cpf: String)
    fun onDelete(cpf: String)
    fun OnPayment(cpf: String)
}