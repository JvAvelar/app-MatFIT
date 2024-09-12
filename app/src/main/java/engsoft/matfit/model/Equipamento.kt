package engsoft.matfit.model

import com.google.gson.annotations.SerializedName

data class Equipamento(
    @SerializedName("id")
    var id: Int = 0,

    @SerializedName("nome")
    var nome: String = "",

    @SerializedName("quantidade")
    var quantidade: Int = 0
)
