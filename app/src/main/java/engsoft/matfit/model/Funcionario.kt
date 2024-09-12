package engsoft.matfit.model

import com.google.gson.annotations.SerializedName

data class Funcionario(
    @SerializedName("cpf")
    var cpf: String = "",

    @SerializedName("nome")
    var nome: String = "",

    @SerializedName("funcao")
    var funcao: String = "",

    @SerializedName("cargaHoraria")
    var cargaHoraria: Int = 0
)
