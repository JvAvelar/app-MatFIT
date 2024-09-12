package engsoft.matfit.model

import android.content.Context
import android.widget.Toast

open class BaseValidacao(val context: Context) {

    fun toast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    fun validarCargaHoraria(carga: Int) = carga > 0

    fun validarNome(nome: String) =
        (nome.isNotBlank() && nome.isNotEmpty() && nome.length >= 4)

    // Faz a validação do esporte
    fun validarEsporte(esporte: String) =
        (esporte.length >= 3 && esporte.isNotBlank() && esporte.isNotEmpty())
    // Faz a validação do cpf
    fun validarCpf(cpfAntigo: String): Boolean {
        // Remove caracteres não numéricos do CPF
        var cpf = cpfAntigo
        cpf = cpf.replace("[^0-9]".toRegex(), "")
            .replace("-", "").replace(".", "")

        // Verifica se o CPF tem 11 dígitos
        if (cpf.length != 11) {
            return false
        }

        // Verifica se todos os dígitos são iguais
        var digitosIguais = true
        for (i in 1..10) {
            if (cpf[i] != cpf[0]) {
                digitosIguais = false
                break
            }
        }
        if (digitosIguais) {
            return false
        }

        // Validação dos dígitos verificadores
        var soma = 0
        for (i in 0..8) {
            soma += (10 - i) * (cpf[i].code - '0'.code)
        }
        var digito1 = 11 - soma % 11
        if (digito1 > 9) {
            digito1 = 0
        }
        soma = 0
        for (i in 0..9) {
            soma += (11 - i) * (cpf[i].code - '0'.code)
        }
        var digito2 = 11 - soma % 11
        if (digito2 > 9) {
            digito2 = 0
        }
        return cpf[9].code - '0'.code == digito1 && cpf[10].code - '0'.code == digito2
    }

    // Faz a validação do nome

}