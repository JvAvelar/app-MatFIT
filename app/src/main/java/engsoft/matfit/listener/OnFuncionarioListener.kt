package engsoft.matfit.listener

interface OnFuncionarioListener {
    fun onUpdate(cpf: String)
    fun onDelete(cpf: String)
}