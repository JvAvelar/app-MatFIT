package engsoft.matfit.model

data class AlunoResponse(
    val cpf: String,
    val nome: String,
    val esporte: String,
    val dataPagamento: String,
    val pagamentoAtrasado: Boolean
)